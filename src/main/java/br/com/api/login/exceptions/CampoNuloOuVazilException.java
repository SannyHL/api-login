package br.com.api.login.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CampoNuloOuVazilException extends RuntimeException{
    private final HttpStatus status;

    public CampoNuloOuVazilException(String campo, HttpStatus status) {
        super("O campo ".concat(campo).concat(" deve ser preenchido"));
        this.status = status;
    }
}
