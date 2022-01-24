package br.edu.ifg.manutencao.coneferserver.service;

import br.edu.ifg.manutencao.coneferserver.entity.Fornecedor;
import br.edu.ifg.manutencao.coneferserver.exception.ConeferException;
import br.edu.ifg.manutencao.coneferserver.exception.RecordNotFoundException;
import br.edu.ifg.manutencao.coneferserver.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService extends GenericoService<Fornecedor> {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public FornecedorRepository getRepository() {
        return this.fornecedorRepository;
    }

    public Page<FornecedorRequest> listarTodosPage(String nome, Pageable pageable) {
        Page<Fornecedor> fornecedors = this.getRepository().listarPorNomePaginado(nome, pageable);
        Page<FornecedorRequest> parserToModel = this.parserToModel(fornecedors, FornecedorRequest.class);
        return parserToModel;
    }

    public Fornecedor salvar(FornecedorRequest fornecedorRequest) {
        Fornecedor entidade = this.parserToEntity(fornecedorRequest, Fornecedor.class);
        this.getRepository().save(entidade);
        return parserToModel(entidade, Fornecedor.class);
    }

    public Fornecedor excluirPeloId(String uuid) throws ConeferException {
        Fornecedor fornecedor = consultarPorUuid(uuid);
        fornecedor.excluirLogicamente();
        Fornecedor atualizar = super.atualizar(fornecedor);
        return parserToModel(atualizar, Fornecedor.class);
    }

    public Fornecedor consultarPorUuid(String uuid) throws RecordNotFoundException {
        return this.getRepository().findByUuid(uuid).orElseThrow(
                () -> new RecordNotFoundException(String.format("Uuid da Fornecedor é inválido: %s", uuid)));
    }

    public Fornecedor consultarPeloUuid(String uuid) {
        Fornecedor entidade = this.consultarPorUuid(uuid);
        Fornecedor fornecedorResponse = parserToModel(entidade, Fornecedor.class);
        return fornecedorResponse;
    }

    public Fornecedor atualizar(FornecedorRequest entidade) throws ConeferException {
        Fornecedor entidadeAtualizar = this.consultarPorUuid(entidade.getUuid());
        super.atualizar(entidadeAtualizar);
        return parserToModel(entidadeAtualizar, Fornecedor.class);
    }
}
