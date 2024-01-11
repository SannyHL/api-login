package br.com.api.login.service.impl;

import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioLoginDTO;
import br.com.api.login.security.JwtAuthenticationResponse;
import br.com.api.login.security.JwtTokenProvider;
import br.com.api.login.service.AutenticacaoService;
import br.com.api.login.service.UsuarioService;
import br.com.api.login.util.MensagensExceptions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioService usuarioService;

    @Override
    public JwtAuthenticationResponse autenticacaoUsuario(UsuarioLoginDTO usuarioLogin, HttpServletRequest request) {
        try {
            List<GrantedAuthority> authorities = new ArrayList<>();

            Usuario usuario = usuarioService.buscarUsuarioPorEmail(usuarioLogin.getEmail());

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioLogin.getEmail(), usuarioLogin.getSenha(), authorities));
            usuario.setUltimoAcesso(new Date());
            usuarioService.salvarUsuario(usuario);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return JwtAuthenticationResponse
                    .builder()
                    .token(jwtTokenProvider.generateToken(authentication)).build();

        } catch (Exception e){

            return JwtAuthenticationResponse.builder()
                    .message(MensagensExceptions.ERRO_AUTENTICACAO).build();

        }

    }

}
