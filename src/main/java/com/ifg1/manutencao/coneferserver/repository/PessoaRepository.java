package com.ifg1.manutencao.coneferserver.repository;

import com.ifg1.manutencao.coneferserver.entity.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends GenericoRepository<Pessoa> {
    List<Pessoa> findByDataFimIsNull();
}
