package br.edu.ifg.manutencao.coneferserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "default_gen", sequenceName = "produto_id_seq", allocationSize = 1)
public class Produto extends Entidade {

    @Column(nullable = false)
    private String nome;

    @Column(scale = 2)
    private BigDecimal precoCusto;

    @Column(scale = 2)
    private BigDecimal precoVenda;

    private BigDecimal estoqueAtual;

    private BigDecimal estoqueMinimo;

    @ManyToOne
    private Unidade unidade;

    private Boolean controlaEstoque;

    @ManyToOne
    private Grupo grupo;
}
