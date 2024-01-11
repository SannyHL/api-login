package br.com.api.login.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="NOME")
    @Size(min = 4, max = 50)
    private String nome;

    @CPF
    @Column(name = "CPF")
    private String cpf;

    @Email
    @Column(name="EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "SENHA")
    @Size(min = 4)
    private String senha;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "USUARIO_CRIACAO_ID")
    private Usuario usuarioCriacao;

    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ALTERACAO_ID")
    private Usuario usuarioAlteracao;

    @Column(name = "DATA_ULTIMA_ALTERACAO")
    private Date dataUltimaAlteracao;

    @Column(name = "ULTIMO_ACESSO")
    private Date ultimoAcesso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

}
