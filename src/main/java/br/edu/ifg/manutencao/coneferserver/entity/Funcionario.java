package br.edu.ifg.manutencao.coneferserver.entity;

import br.edu.ifg.manutencao.coneferserver.entity.enums.EnumEstado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "default_gen", sequenceName = "funcionario_id_seq", allocationSize = 1)
public class Funcionario extends Entidade {

    @OneToOne
    @JoinColumn(name = "pessoa_id", unique = true)
    private Pessoa pessoa;

    private EnumEstado estadoCivil;

    private LocalDateTime dataAdmissao;

    private String ctps;

    @Column(scale = 2)
    private BigDecimal salario;

    @ManyToOne
    private Cargo cargo;

    @ManyToOne
    private Grupo grupo;




}
