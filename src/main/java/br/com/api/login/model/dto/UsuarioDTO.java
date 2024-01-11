package br.com.api.login.model.dto;

import br.com.api.login.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private Boolean ativo;
    private Integer usuarioCriacaoId;
    private Date dataCriacao;
    private Integer usuarioAlteracaoId;
    private Date dataUltimaAlteracao;
    private Date ultimoAcesso;
    private List<Role> roles;


    public UsuarioDTO(Integer id, String nome, String cpf, String email, String telefone, Boolean ativo, Integer usuarioCriacaoId, Date dataCriacao, Integer usuarioAlteracaoId, Date dataUltimaAlteracao, Date ultimoAcesso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
        this.usuarioCriacaoId = usuarioCriacaoId;
        this.dataCriacao = dataCriacao;
        this.usuarioAlteracaoId = usuarioAlteracaoId;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
        this.ultimoAcesso = ultimoAcesso;
    }
}
