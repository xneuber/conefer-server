package br.edu.ifg.manutencao.coneferserver.repository;

import br.edu.ifg.manutencao.coneferserver.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends GenericoRepository<Pessoa> {
    List<Pessoa> findByDataFimIsNull();
}
