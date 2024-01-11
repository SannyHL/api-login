package br.com.api.login.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    private String message;
    private HttpStatus httpStatus;
    private Integer code;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
