package br.com.api.login.controller;



import br.com.api.login.model.dto.UsuarioLoginDTO;
import br.com.api.login.service.AutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;


    @PostMapping
    public ResponseEntity<?> autenticacaoUsuario(@RequestBody UsuarioLoginDTO usuarioLogin, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(autenticacaoService.autenticacaoUsuario(usuarioLogin, request));
        } catch (Exception e) {
            throw e;
        }
    }


}
