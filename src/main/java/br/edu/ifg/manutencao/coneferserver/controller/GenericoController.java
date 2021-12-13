package br.edu.ifg.manutencao.coneferserver.controller;

import br.edu.ifg.manutencao.coneferserver.entity.Entidade;
import br.edu.ifg.manutencao.coneferserver.service.GenericoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericoController<E extends Entidade> {

    public static final String CONTENT_RANGE_HEADER = "Content-Range";

    public abstract GenericoService<E> getService();

    protected HttpHeaders getHttpHeaders(Object id) {
        URI location;

        if (id == null) {
            location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        } else {
            location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, location.toString());
        return responseHeaders;
    }

    static class ResponseHeaderPaginable {
        private final Integer page;
        private final Page<?> list;
        private Integer total;
        private Integer offset;
        private HttpStatus status;

        public ResponseHeaderPaginable(Integer page, Page<?> list) {
            this.page = page;
            this.list = list;
        }

        public HttpStatus getStatus() {
            return status;
        }

        private HttpStatus readStatus(Page<?> list, Integer total, Integer offset) {
            return list.getSize() == 0 ? HttpStatus.NO_CONTENT : ((list.getSize() + offset < total ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK));
        }

        protected ResponseHeaderPaginable invoke() {
            total = Math.toIntExact(list.getTotalElements());
            offset = Optional.ofNullable(page).orElse(0);
            status = readStatus(list, total, offset);
            return this;
        }

        protected String responsePageRange() {
            return offset + "-" + offset + list.getSize() + "/" + total;
        }
    }

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<vet.length; i++) {
            list.add(Integer.parseInt(vet[i]));
        }
        return list;
    }
}


