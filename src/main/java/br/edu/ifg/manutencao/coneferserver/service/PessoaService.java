package br.edu.ifg.manutencao.coneferserver.service;

import br.edu.ifg.manutencao.coneferserver.entity.Pessoa;
import br.edu.ifg.manutencao.coneferserver.exception.ConeferException;
import br.edu.ifg.manutencao.coneferserver.exception.RecordNotFoundException;
import br.edu.ifg.manutencao.coneferserver.model.request.PessoaPutRequest;
import br.edu.ifg.manutencao.coneferserver.model.request.PessoaRequest;
import br.edu.ifg.manutencao.coneferserver.model.response.PessoaResponse;
import br.edu.ifg.manutencao.coneferserver.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService extends GenericoService<Pessoa> {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public PessoaRepository getRepository() {
        return this.pessoaRepository;
    }

    public Page<PessoaRequest> listarTodosPage(String nome, Pageable pageable) {
        Page<Pessoa> pessoas = this.getRepository().listarPorNomePaginado(nome, pageable);
        Page<PessoaRequest> parserToModel = this.parserToModel(pessoas, PessoaRequest.class);
        return parserToModel;
    }

    public PessoaResponse salvar(PessoaRequest pessoaRequest) {
        Pessoa entidade = this.parserToEntity(pessoaRequest, Pessoa.class);
        this.getRepository().save(entidade);
        return parserToModel(entidade, PessoaResponse.class);
    }

    public PessoaResponse excluirPeloId(String uuid) throws ConeferException {
        Pessoa pessoa = consultarPorUuid(uuid);
        pessoa.excluirLogicamente();
        Pessoa atualizar = super.atualizar(pessoa);
        return parserToModel(atualizar, PessoaResponse.class);
    }

    public Pessoa consultarPorUuid(String uuid) throws RecordNotFoundException {
        return this.getRepository().findByUuid(uuid).orElseThrow(
                () -> new RecordNotFoundException(String.format("Uuid da Pessoa é inválido: %s", uuid)));
    }

    public PessoaResponse consultarPeloUuid(String uuid) {
        Pessoa entidade = this.consultarPorUuid(uuid);
        PessoaResponse pessoaResponse = parserToModel(entidade, PessoaResponse.class);
        return pessoaResponse;
    }

    public PessoaResponse atualizar(PessoaRequest entidade) throws ConeferException {
        Pessoa entidadeAtualizar = this.consultarPorUuid(entidade.getUuid());
        super.atualizar(entidadeAtualizar);
        return parserToModel(entidadeAtualizar, PessoaResponse.class);
    }
}
