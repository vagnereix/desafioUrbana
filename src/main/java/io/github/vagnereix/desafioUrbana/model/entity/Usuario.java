package io.github.vagnereix.desafioUrbana.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty(message = "{campo.nome.usuario.obrigatorio}")
    private String nome;

    @Column(unique = true)
    @Email(message = "{campo.email.usuario.invalido}")
    @NotEmpty(message = "{campo.email.usuario.obrigatorio}")
    private String email;

    @Column
    @NotEmpty(message = "{campo.senha.usuario.obrigatorio}")
    private String senha;

    @OneToMany //Um usuario pode ter vários cartões
    @JoinColumn(name = "id_cartao")
    @JsonIgnoreProperties
    @JsonManagedReference
    private List<Cartao> cartoes = new ArrayList<>();

    //adicionar novo cartao à lista de cartoes
    public void addCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }
}
