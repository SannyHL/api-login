package br.com.api.login.security;


import br.com.api.login.model.DetalhesUsuario;
import br.com.api.login.util.ConstantesUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final GeradorSecretyKey geradorSecretyKey;

    public String generateToken( Authentication authResult) {

        byte[] hmac512Key = geradorSecretyKey.getHmac512KeyByte();

        DetalhesUsuario usuario = (DetalhesUsuario) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getUsuario().getId().toString())
                .withClaim("email", usuario.getUsuario().getEmail())
                .withClaim("nome", usuario.getUsuario().getNome())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + ConstantesUtils.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(hmac512Key));

        return token;
    }

}
