package br.com.api.login.repository;


import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);

    @Query("SELECT new br.com.api.login.model.dto.UsuarioDTO(usuario.id, " +
            "usuario.nome, " +
            "usuario.cpf, " +
            "usuario.email, " +
            "usuario.telefone, " +
            "usuario.ativo, " +
            "usuario.usuarioCriacao.id, " +
            "usuario.dataCriacao, " +
            "usuario.usuarioAlteracao.id, " +
            "usuario.dataUltimaAlteracao, " +
            "usuario.ultimoAcesso, " +
            "usuario.roles) " +
            "FROM Usuario usuario " +
            "WHERE usuario.id = :id")
    Optional<UsuarioDTO> buscarUsuarioPorId(@Param("id") Integer id);

    @Query("SELECT new br.com.api.login.model.dto.UsuarioDTO(usuario.id, " +
            "usuario.nome, " +
            "usuario.cpf, " +
            "usuario.email, " +
            "usuario.telefone, " +
            "usuario.ativo, " +
            "usuario.usuarioCriacao.id, " +
            "usuario.dataCriacao, " +
            "usuario.usuarioAlteracao.id, " +
            "usuario.dataUltimaAlteracao, " +
            "usuario.ultimoAcesso) " +
            "FROM Usuario usuario")
    List<UsuarioDTO> buscarListaUsuarios();
}
