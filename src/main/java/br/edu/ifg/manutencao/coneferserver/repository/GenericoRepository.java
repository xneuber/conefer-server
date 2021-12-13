package br.edu.ifg.manutencao.coneferserver.repository;

import br.edu.ifg.manutencao.coneferserver.entity.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface GenericoRepository<E extends Entidade> extends JpaRepository<E, Long> {

    Optional<E> findByUuidAndDataFimIsNull(String uuid);
}
