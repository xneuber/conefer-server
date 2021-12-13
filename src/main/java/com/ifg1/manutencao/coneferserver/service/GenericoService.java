package com.ifg1.manutencao.coneferserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.ifg1.manutencao.coneferserver.entity.Entidade;
import com.ifg1.manutencao.coneferserver.exception.ConeferException;
import com.ifg1.manutencao.coneferserver.exception.ExceptionManipulator;
import com.ifg1.manutencao.coneferserver.exception.InfoMessage;
import com.ifg1.manutencao.coneferserver.model.EntidadeModel;
import com.ifg1.manutencao.coneferserver.repository.GenericoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Classe de service genérica. Utilizada para criar um CRUD da entidade.
 *
 * @param <E> Entidade a ser manipulada.
 */
public abstract class GenericoService<E extends Entidade> extends ExceptionManipulator {

    protected abstract GenericoRepository<E> getRepository();

    public void preConsultarPeloId(Long id) throws ConeferException {
        // implementar se necessário.
    }

    public Optional<E> consultarPeloId(Long id) throws ConeferException {
        preConsultarPeloId(id);
        lancarExcecaoSeObjetoNulo(id, "Id não pode ser nulo");
        lancarExcecaoSeObjetoNulo(id.equals(0L), "Id não pode ser zero");
        return getRepository().findById(id);
    }

    public Optional<E> consultarPorUuidAtivo(String uuid) throws ConeferException {
        lancarExcecaoSeObjetoNulo(uuid, "Uuid não pode ser nulo");
        lancarExcecaoSeObjetoNulo(uuid.equals(0L), "Uuid não pode ser zero");
        return getRepository().findByUuidAndDataFimIsNull(uuid);
    }

    public void preAtualizar(E objeto) throws ConeferException {
        // implementar se necessário.
    }

    public void posAtualizar(E objeto) throws ConeferException {
        // implementar se necessário.
    }

    public E atualizar(E objeto) throws ConeferException {
        preAtualizar(objeto);
        E retorno = getRepository().save(objeto);
        posAtualizar(objeto);
        return retorno;
    }

    public void preIncluir(E objeto) throws ConeferException {
        // implementar se necessário.
    }

    public void posIncluir(E objeto) throws ConeferException {
        // implementar se necessário.
    }

    public E incluir(E objeto) throws ConeferException {
        preIncluir(objeto);
        E retorno = getRepository().save(objeto);
        posIncluir(objeto);
        return retorno;
    }

    public void preExcluir(Long id) throws ConeferException {
        // implementar se necessário.
    }

    public void preExcluir(String uuid) throws ConeferException {
        // implementar se necessário.
    }

    public InfoMessage excluirPeloId(Long id) throws ConeferException {
        preExcluir(id);
        getRepository().deleteById(id);
        posExcluir(id);
        return new InfoMessage(200, "Excluído com sucesso");
    }


    public void posExcluir(Long id) throws ConeferException {
        // implementar se necessário.
    }

    public void posExcluir(String uuid) throws ConeferException {
        // implementar se necessário.
    }

    public void preListar() throws ConeferException {
        // implementar se necessário.
    }

    public List<E> listar() throws ConeferException {
        preListar();
        return getRepository().findAll();
    }

    public void detachObject(Object entidade) {
        //
    }

    public String parserJsonToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getObject(String json, Class clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <S extends Entidade, T extends EntidadeModel> T parserToModel(S source, Class<T> targetClass){
        try {
            Optional.ofNullable(source).orElseThrow(() -> new ConeferException("O objeto informado está nullo"));

            return new ModelMapper().map(source, targetClass);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <S extends EntidadeModel, T extends Entidade> T parserToEntity(S source, Class<T> targetClass){

        try {
            Optional.ofNullable(source).orElseThrow(() -> new ConeferException("O objeto informado está nullo"));

            return new ModelMapper().map(source, targetClass);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends EntidadeModel> Page<T> parserToModel(Page<?> lista, Type targetClass){
        return new ModelMapper().map(lista,  new TypeToken<Page<Type>>() {}.getType());
    }

    public <T extends EntidadeModel> List<T> parserToModel(List<?> lista, Type targetClass){
        return new ModelMapper().map(lista,  new TypeToken<List<Type>>() {}.getType());
    }

    public static <S, T> List<T> parserToModel(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(new ModelMapper().map(s, targetClass));
        }
        return list;
    }

    public <T> List<T> parserToModelList(String json, Class<T> clazz){
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + clazz.getName() + ";");
            T[] objects = new ObjectMapper().readValue(json, arrayClass);
            return Arrays.asList(objects);
        } catch (Exception e) {
            return null;
        }
    }
}
