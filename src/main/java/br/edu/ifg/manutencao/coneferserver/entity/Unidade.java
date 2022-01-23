package br.edu.ifg.manutencao.coneferserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "default_gen", sequenceName = "unidade_id_seq", allocationSize = 1)
public class Unidade extends Entidade {

    private String descricao;

    @Column(nullable = false)
    private String unidade;

    @Column(nullable = false)
    private Boolean isInt;
}
