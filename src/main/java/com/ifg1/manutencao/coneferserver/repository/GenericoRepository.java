package com.ifg1.manutencao.coneferserver.repository;

import com.ifg1.manutencao.coneferserver.entity.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface GenericoRepository<E extends Entidade> extends JpaRepository<E, Long> {

    Optional<E> findByUuidAndDataFimIsNull(String uuid);
}
