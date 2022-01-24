package br.edu.ifg.manutencao.coneferserver.repository;

import br.edu.ifg.manutencao.coneferserver.entity.Fornecedor;
import br.edu.ifg.manutencao.coneferserver.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends GenericoRepository<Pessoa> {

    Optional<Fornecedor> findByUuid(String uuid);

    @Query(value = "select new br.edu.ifg.manutencao.coneferserver.model.response.PessoaResponse(p.nome, p.uuid) " +
            "from Pessoa p where p.nome like %:nome%")
    Page<Pessoa> listarPorNomePaginado(@Param("nome") String nome, Pageable pageable);

}
