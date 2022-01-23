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
@SequenceGenerator(name = "default_gen", sequenceName = "usuario_id_seq", allocationSize = 1)
public class Usuario extends Entidade {

    @OneToOne
    @JoinColumn(name = "grupo_id", unique = true)
    private Funcionario funcionario;

    private String usuario;

    private String senha;

    private Boolean podeAcessar;

}
