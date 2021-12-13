package br.edu.ifg.manutencao.coneferserver.service;

import br.edu.ifg.manutencao.coneferserver.entity.Pessoa;
import br.edu.ifg.manutencao.coneferserver.model.response.PessoaResponse;
import br.edu.ifg.manutencao.coneferserver.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService extends GenericoService<Pessoa> {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public PessoaRepository getRepository() { return this.pessoaRepository; }

    public List<PessoaResponse> listarDataEqualsNull() {
        List<Pessoa> lista = getRepository().findByDataFimIsNull();
        return parserToModel(lista, PessoaResponse.class);
    }
}
