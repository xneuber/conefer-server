package br.edu.ifg.manutencao.coneferserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "default_gen", sequenceName = "cliente_id_seq", allocationSize = 1)
public class Cliente extends Entidade {

    @OneToOne
    @JoinColumn(name = "pessoa_id", unique = true)
    private Pessoa pessoa;

    private String rg;

    private LocalDateTime nascimento;

    private String pai;

    private String mae;

    private String localDeTrabalho;
}
