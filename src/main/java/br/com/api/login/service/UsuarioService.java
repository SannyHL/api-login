package br.com.api.login.service;


import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    Usuario buscarUsuarioPorEmail(String email);

    void salvarUsuario(Usuario usuario);

    void criarUsuario(UsuarioDTO usuarioDTO);

    UsuarioDTO buscarUsuarioPorId(Integer id);

    void editarUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> buscarListaUsuarios();
}
