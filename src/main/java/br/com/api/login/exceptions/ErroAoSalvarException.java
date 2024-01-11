package br.com.api.login.exceptions;

public class ErroAoSalvarException extends RuntimeException{

    public ErroAoSalvarException(String campo, Throwable code) {
        super("Erro ao salvar ".concat(campo), code);
    }

}
