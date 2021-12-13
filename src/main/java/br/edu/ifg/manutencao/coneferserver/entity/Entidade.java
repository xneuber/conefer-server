package br.edu.ifg.manutencao.coneferserver.entity;

import br.edu.ifg.manutencao.coneferserver.util.UtilDataHora;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class Entidade implements Serializable {

    private static final long serialVersionUID = 3270601256011818010L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Access(AccessType.PROPERTY)
    protected Long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    private LocalDateTime dataFim;

    public Entidade() {
    }

    public Entidade(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidade)) return false;
        Entidade entidade = (Entidade) o;
        return id.equals(entidade.id) && uuid.equals(entidade.uuid) && dataCriacao.equals(entidade.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, dataCriacao);
    }

    @PrePersist
    public void inserirDataCriacaoAtualizacao() {
        this.setDataCriacao(UtilDataHora.obterDataHoraAtualDeBrasilia());
        this.setDataAtualizacao(UtilDataHora.obterDataHoraAtualDeBrasilia());

        if(Objects.isNull(this.uuid)){
            this.uuid = UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    public void atualizarDataAtualizacao() {
        this.setDataAtualizacao(UtilDataHora.obterDataHoraAtualDeBrasilia());
    }

    public void excluirLogicamente(){
        this.dataFim = UtilDataHora.obterDataHoraAtualDeBrasilia();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}

