package br.edu.ifg.manutencao.coneferserver.model.request;

import br.edu.ifg.manutencao.coneferserver.model.EntidadeModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PessoaRequest extends EntidadeModel {

    private Long id;

    @NotEmpty(message = "Informe o nome.")
    @ApiModelProperty(value = "Nome da Pessoa", required = true)
    private String nome;

    @NotEmpty(message = "Informe a descrição.")
    @ApiModelProperty(value = "Sobrenome", required = true)
    private String sobrenome;

}
