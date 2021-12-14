package br.edu.ifg.manutencao.coneferserver.model.response;

import br.edu.ifg.manutencao.coneferserver.model.EntidadeModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResponse extends EntidadeModel {

    private String nome;
    private String uuid;
    private String descricao;

    public PessoaResponse(String nome, String uuid){
        this.nome = nome;
        this.uuid = uuid;
    }
}
