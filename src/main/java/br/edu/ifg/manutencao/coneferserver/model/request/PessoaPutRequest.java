package br.edu.ifg.manutencao.coneferserver.model.request;

import br.edu.ifg.manutencao.coneferserver.model.EntidadeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PessoaPutRequest extends EntidadeModel {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String uuid;

    @NotEmpty(message = "Informe o nome.")
    @ApiModelProperty(value = "Descrição do documento", required = true)
    private String nome;

    @NotEmpty(message = "Informe a descrição.")
    @ApiModelProperty(value = "Descrição do documento", required = true)
    private String descricao;

}
