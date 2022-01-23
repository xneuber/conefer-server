package br.edu.ifg.manutencao.coneferserver.entity;

import br.edu.ifg.manutencao.coneferserver.entity.enums.EnumEstado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "default_gen", sequenceName = "pessoa_id_seq", allocationSize = 1)
public class Pessoa extends Entidade {

    private final static String CEP_PATTERN = "\\n\\n\\n\\n\\n-\\n\\n\\n";

    @Column(nullable = false)
    private String nome;

    private String endereco;

    private String bairro;

    private String cidade;

    @Pattern(regexp = CEP_PATTERN)
    private String cep;

    @Enumerated(EnumType.STRING)
    private EnumEstado estado;

    @Column(nullable = false, length = 14)
    @Size(min = 14, max = 18)
    private String cpfCnpj;

    @Column(nullable = false)
    private Boolean isPJ;

    @Column(length = 14)
    private String telefone;


}
