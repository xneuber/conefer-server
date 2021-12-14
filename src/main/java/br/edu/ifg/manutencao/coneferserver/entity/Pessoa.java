package br.edu.ifg.manutencao.coneferserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "default_gen", sequenceName = "grupo_documento_id_seq", allocationSize = 1)
public class Pessoa extends Entidade {

    private String nome;

    private String descricao;
}
