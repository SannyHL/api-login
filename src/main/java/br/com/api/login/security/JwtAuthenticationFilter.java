package br.com.api.login.security;

import br.com.api.login.util.ConstantesUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static int ID_USUARIO_LOGADO;
    private final GeradorSecretyKey geradorSecretyKey;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            try {
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(geradorSecretyKey.getHmac512KeyByte()))
                        .build()
                        .verify(token);

                String username = decodedJWT.getSubject();
                int id = Integer.parseInt(decodedJWT.getClaim("id").asString());
                String nome = decodedJWT.getClaim("nome").asString();
                String email = decodedJWT.getClaim("email").asString();
                ID_USUARIO_LOGADO = id;
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, null);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                logger.error("Erro ao validar Token", e);
            }

        }
        filterChain.doFilter(request, response);
    }
}
