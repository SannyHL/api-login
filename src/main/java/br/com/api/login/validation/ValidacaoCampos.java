package br.com.api.login.validation;

import br.com.api.login.exceptions.CampoNuloOuVazilException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ValidacaoCampos {

    public static void validarCampoNuloOuVazio(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new CampoNuloOuVazilException(nomeCampo, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public static  void validarListaNulaOuVazia(List<?> lista, String nomeCampo) {
        if (lista == null || lista.isEmpty()) {
            throw new CampoNuloOuVazilException(nomeCampo, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
