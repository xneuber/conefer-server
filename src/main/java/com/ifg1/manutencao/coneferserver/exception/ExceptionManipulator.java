package com.ifg1.manutencao.coneferserver.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Collection;

public class ExceptionManipulator {

    protected void lancarExcecaoSeObjetoNaoNulo(Object objeto, String mensagem) throws ConeferException {
        if (objeto != null) {
            lancarExcecaoSemCondicao(mensagem);
        }
    }

    protected void lancarExcecaoSeObjetoNulo(Object objeto, String mensagem) throws ConeferException {
        if (objeto == null) {
            lancarExcecaoSemCondicao(mensagem);
        }
    }

    protected void lancarExcecaoSeStringVazia(Object objeto, String mensagem) throws ConeferException {
        if (objeto == null) {
            lancarExcecaoSemCondicao(mensagem);
        }
        if (!(objeto instanceof String)) {
            lancarExcecaoSemCondicao(mensagem);
        }
        lancarExcecaoSeCondicaoVerdadeira(StringUtils.isEmpty((String) objeto), mensagem);
    }

    protected void lancarExcecaoSeCondicaoVerdadeira(boolean condicao, String mensagem) throws ConeferException {
        if (condicao) {
            lancarExcecaoSemCondicao(mensagem);
        }
    }

    protected void lancarExcecaoSemCondicao(String mensagem) throws ConeferException {
        throw new ConeferException(HttpStatus.BAD_REQUEST.value(), mensagem);
    }

    protected void lancarExcecaoSeLongNullOuZero(Long valorLong, String mensagem) throws ConeferException {
        lancarExcecaoSeCondicaoVerdadeira(valorLong == null || valorLong.equals(0L), mensagem);
    }

    protected void lancarExcecaoSeIntegerNullOuZero(Integer valorInteger, String mensagem) throws ConeferException {
        lancarExcecaoSeCondicaoVerdadeira(valorInteger == null || valorInteger.equals(0), mensagem);
    }

    protected void lancarExcecaoSeBigDecimalNullOuZero(BigDecimal valorBigDecimal, String mensagem) throws ConeferException {
        lancarExcecaoSeCondicaoVerdadeira(valorBigDecimal == null || BigDecimal.ZERO.equals(valorBigDecimal) || BigDecimal.ZERO.compareTo(valorBigDecimal) >= 0, mensagem);
    }

    protected void lancarExcecaoSeListaVaziaOuNula(Collection<?> lista, String mensagemListaNula, String mensagemListaVazia) throws ConeferException {
        lancarExcecaoSeObjetoNulo(lista, mensagemListaNula);
        lancarExcecaoSeCondicaoVerdadeira(lista.isEmpty(), mensagemListaVazia);
    }

    protected void lancarExcecaoSeListaVaziaOuNula(Collection<?> lista, String mensagemGenerica) throws ConeferException {
        lancarExcecaoSeObjetoNulo(lista, mensagemGenerica);
        lancarExcecaoSeCondicaoVerdadeira(lista.isEmpty(), mensagemGenerica);
    }

    protected void lancarExcecaoSeListaVaziaOuNula(Collection<?> lista) throws ConeferException {
        lancarExcecaoSeObjetoNulo(lista, "Lista não pode nula");
        lancarExcecaoSeCondicaoVerdadeira(lista.isEmpty(), "Lista não pode ser vazia");
    }

    protected void lancarExcecaoSeListaNula(Collection<?> lista) throws ConeferException {
        lancarExcecaoSeObjetoNulo(lista, "Lista não pode nula");
    }
}
