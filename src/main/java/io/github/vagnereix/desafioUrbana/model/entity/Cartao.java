package io.github.vagnereix.desafioUrbana.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.vagnereix.desafioUrbana.model.entity.enums.TipoCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties
    @JsonBackReference
    private Usuario usuario;

    @Id
    @Column(unique = true, length = 9)
    @NotNull(message = "{campo.numero.cartao.obrigatorio}")
    @NumberFormat
    private Long numero;

    @Column
//    não é preciso pois o nome do usuário é obrigatório e estou usando o mesmo para setar no cartão
//    @NotEmpty(message = "{campo.nome.cartao.obrigatorio}")
    private String nome;

    @Column
    private boolean status = true;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{campo.tipo.cartao.obrigatorio}")
    private TipoCartao tipo;
}
