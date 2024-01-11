package br.com.api.login.controller;

import br.com.api.login.service.UsuarioService;
import br.com.api.login.model.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.editarUsuario(usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarListaUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarListaUsuarios());
    }
}
