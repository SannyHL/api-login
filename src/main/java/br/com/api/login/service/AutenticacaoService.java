package br.com.api.login.service;

import br.com.api.login.model.dto.UsuarioLoginDTO;
import br.com.api.login.security.JwtAuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AutenticacaoService {

    JwtAuthenticationResponse autenticacaoUsuario(UsuarioLoginDTO usuarioLogin, HttpServletRequest request);
}
