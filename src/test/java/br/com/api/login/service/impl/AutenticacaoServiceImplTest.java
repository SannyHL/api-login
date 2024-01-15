package br.com.api.login.service.impl;

import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioLoginDTO;
import br.com.api.login.security.JwtTokenProvider;
import br.com.api.login.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AutenticacaoServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private AutenticacaoServiceImpl autenticacaoService;

    @Test
    void testAutenticacaoUsuario() {
        UsuarioLoginDTO usuarioLogin = UsuarioLoginDTO.builder()
                .email("usuarioteste@teste.com")
                .senha("123456")
                .build();

        List<GrantedAuthority> authorities = new ArrayList<>();
        Usuario usuario = new Usuario();

        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioLogin.getEmail(), usuarioLogin.getSenha(), authorities);

    }
}