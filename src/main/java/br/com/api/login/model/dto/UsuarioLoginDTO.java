package br.com.api.login.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {
    private String email;
    private String senha;
}