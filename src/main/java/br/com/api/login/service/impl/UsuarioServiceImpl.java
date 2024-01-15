package br.com.api.login.service.impl;

import br.com.api.login.exceptions.ErroAoSalvarException;
import br.com.api.login.mapper.UsuarioMapper;
import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioDTO;
import br.com.api.login.repository.UsuarioRepository;
import br.com.api.login.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static br.com.api.login.security.JwtAuthenticationFilter.ID_USUARIO_LOGADO;
import static br.com.api.login.validation.ValidacaoCampos.validarCampoNuloOuVazio;
import static br.com.api.login.validation.ValidacaoCampos.validarListaNulaOuVazia;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário localizado."));
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(Integer id) {
        return usuarioRepository.buscarUsuarioPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum usuário localizado."));
    }

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }
    @Override
    public void criarUsuario(UsuarioDTO usuarioDTO) {
        try{
            if(usuarioRepository.existsByCpf(usuarioDTO.getCpf())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
            } else if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
            }
            validarCampoNuloOuVazio(usuarioDTO.getSenha(), "senha");
            validarCampoNuloOuVazio(usuarioDTO.getEmail(), "email");
            validarCampoNuloOuVazio(usuarioDTO.getNome(), "nome");
            validarCampoNuloOuVazio(usuarioDTO.getCpf(), "cpf");
            validarListaNulaOuVazia(usuarioDTO.getRoles(), "role");

                usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
                usuarioDTO.setUltimoAcesso(new Date());
                usuarioDTO.setDataCriacao(new Date());
                usuarioDTO.setDataUltimaAlteracao(new Date());
                usuarioDTO.setUsuarioCriacaoId(ID_USUARIO_LOGADO);
                usuarioDTO.setUsuarioAlteracaoId(ID_USUARIO_LOGADO);

                salvarUsuario(usuarioMapper.dtoToEntity(usuarioDTO));


        } catch (ResponseStatusException e){
            throw e;
        } catch (ErroAoSalvarException e){
            throw new ErroAoSalvarException("Usuário", e);
        }

    }
    @Override
    public void editarUsuario(UsuarioDTO usuarioDTO) {

        UsuarioDTO usuarioNoBanco = buscarUsuarioPorId(usuarioDTO.getId());

        if(usuarioRepository.existsByCpf(usuarioDTO.getCpf()) && !usuarioNoBanco.getCpf().equals(usuarioDTO.getCpf())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
        } else if (usuarioRepository.existsByEmail(usuarioDTO.getEmail()) && !usuarioNoBanco.getEmail().equals(usuarioDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        validarCampoNuloOuVazio(usuarioDTO.getSenha(), "senha");
        validarCampoNuloOuVazio(usuarioDTO.getEmail(), "email");
        validarCampoNuloOuVazio(usuarioDTO.getNome(), "nome");
        validarCampoNuloOuVazio(usuarioDTO.getCpf(), "cpf");
        validarListaNulaOuVazia(usuarioDTO.getRoles(), "role");

        usuarioDTO.setUsuarioCriacaoId(usuarioNoBanco.getUsuarioCriacaoId());
        usuarioDTO.setUsuarioAlteracaoId(ID_USUARIO_LOGADO);
        usuarioDTO.setDataUltimaAlteracao(new Date());

        try{
            usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
            usuarioDTO.setUltimoAcesso(new Date());
            usuarioDTO.setDataCriacao(new Date());
            usuarioDTO.setDataUltimaAlteracao(new Date());

            salvarUsuario(usuarioMapper.dtoToEntity(usuarioDTO));
        } catch (ResponseStatusException e){
            throw new ErroAoSalvarException("Usuário", e);
        }
    }

    @Override
    public List<UsuarioDTO> buscarListaUsuarios() {
        try {
            return usuarioRepository.buscarListaUsuarios();
        } catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar lista de usuários");
        }
    }


}
