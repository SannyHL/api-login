package br.com.api.login.service.impl;

import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioDTO;
import br.com.api.login.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    UsuarioServiceImpl usuarioServiceImpl;
    @Mock
    UsuarioRepository usuarioRepository;

    UsuarioDTO usuarioDTO;
    Usuario usuario;
    @BeforeEach
    public void setUp(){
        usuario = Usuario.builder()
                .id(2)
                .nome("Celma")
                .email("teste@teste.com")
                .cpf("99999999901")
                .telefone("3333333333")
                .senha("123456")
                .build();

        usuarioDTO = UsuarioDTO.builder()
                .id(2)
                .nome("Celma")
                .email("teste@teste.com")
                .cpf("99999999901")
                .telefone("3333333333")
                .senha("123456")
                .build();
    }

    @Test
    @DisplayName("Sucesso ao buscar usuario por email.")
    void buscarUsuarioPorEmail() {
        when(usuarioRepository.findByEmail(this.usuario.getEmail())).thenReturn(Optional.ofNullable(this.usuario));
        Usuario usuarioRetornado = usuarioServiceImpl.buscarUsuarioPorEmail(this.usuario.getEmail());

        assertNotNull(usuarioRetornado);
        assertEquals(Usuario.class, usuarioRetornado.getClass());
        assertEquals(this.usuario, usuarioRetornado);
        verify(usuarioRepository).findByEmail((this.usuario.getEmail()));
        verifyNoMoreInteractions(usuarioRepository);

    }

    @Test
    @DisplayName("Verificar Exception lançada ao buscar usuario por email e ele não for encontrado.")
    void verificarExceptionCasoUsuarioPorEmailNaoSejaEncontrado() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        final UsernameNotFoundException e = assertThrows(UsernameNotFoundException.class, () -> {
            usuarioServiceImpl.buscarUsuarioPorEmail("teste1@teste.com");
        });

        assertThat(e.getMessage(), equalTo("Nenhum usuário localizado."));
    }

    @Test
    @DisplayName("Sucesso ao buscar usuario por id.")
    void buscarUsuarioPorId() {
        when(usuarioRepository.buscarUsuarioPorId(this.usuarioDTO.getId())).thenReturn(Optional.ofNullable(this.usuarioDTO));

        UsuarioDTO usuarioDTORetornado = usuarioServiceImpl.buscarUsuarioPorId(this.usuarioDTO.getId());
        assertNotNull(usuarioDTORetornado);
        assertEquals(UsuarioDTO.class, usuarioDTORetornado.getClass());
        assertEquals(this.usuarioDTO, usuarioDTORetornado);
        verify(usuarioRepository).buscarUsuarioPorId((this.usuarioDTO.getId()));
        verifyNoMoreInteractions(usuarioRepository);
    }

    @Test
    @DisplayName("Verificar Excepion lançada ao buscar usuario por Id e não for encontrado.")
    void buscarUsuarioPorIdLancaException() {
        when(usuarioRepository.buscarUsuarioPorId(anyInt())).thenReturn(Optional.empty());

        final ResponseStatusException e = assertThrows(ResponseStatusException.class, () -> {
            usuarioServiceImpl.buscarUsuarioPorId(1);
        });

        assertThat(e.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(e.getMessage(), equalTo("400 BAD_REQUEST \"Nenhum usuário localizado.\""));
        assertThat(e.getReason(), equalTo("Nenhum usuário localizado."));
    }

    @Test
    void salvarUsuario() {
    }

    @Test
    void criarUsuario() {
    }

    @Test
    void editarUsuario() {
    }

    @Test
    void buscarListaUsuarios() {
    }
}