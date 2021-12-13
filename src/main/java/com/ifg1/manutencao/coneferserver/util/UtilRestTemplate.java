package com.ifg1.manutencao.coneferserver.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Slf4j
@Component
public class UtilRestTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilRestTemplate.class);
    public static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado {}";
    public static final String ERRO_ACESSAR_CLIENT = "Erro ao acessar o client";

    private Integer connectTimeout = 3000;
    private Integer readTimeout = 6000;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public UtilRestTemplate(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate(simpleClientHttpRequestFactory());
        this.objectMapper = objectMapper;
    }

    public <T> T getForEntity(String url,
                              String authHeader,
                              Class responseType) {

        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(url).encode().build().toUri();

            log.info("GET uri:{}", uri);

            JavaType javaType = objectMapper.getTypeFactory().constructType(responseType);

            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(getHttpHeaders(authHeader)), String.class);

            return lerValor(response, javaType);

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info(NENHUM_REGISTRO_ENCONTRADO, url);
            } else {
                log.info(ERRO_ACESSAR_CLIENT, ex.getMessage());
            }
        }

        return null;
    }

    public <T> List<T> getEntityForList(String url,
                                        String authHeader,
                                        Class responseType) {
        try {

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(getHttpHeaders(authHeader)), String.class);

            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, responseType);

            return lerValor(response, collectionType);

        } catch (HttpClientErrorException exception) {

            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOGGER.info(NENHUM_REGISTRO_ENCONTRADO, url);
            } else {
                LOGGER.info(ERRO_ACESSAR_CLIENT, exception.getMessage());
            }
        }
        return null;
    }

    @Nullable
    public <T, R> R postEntity(String url,
                               T queryMap,
                               ParameterizedTypeReference<R> responseType) {

        log.info("POST url:{},param:{}", url, queryMap);

        HttpHeaders authHeader = new HttpHeaders();
        authHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>(queryMap, authHeader);
        ResponseEntity<R> exchange = null;

        try {

            exchange = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.info(NENHUM_REGISTRO_ENCONTRADO, url);
            } else {
                log.info(ERRO_ACESSAR_CLIENT, ex.getMessage());
            }
        }
        return exchange.getBody();
    }

    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    public <T> T getForEntity(Class<T> clazz, String url, Object... uriVariables) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
            JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
            return lerValor(response, javaType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOGGER.info(NENHUM_REGISTRO_ENCONTRADO, url);
            } else {
                LOGGER.info(ERRO_ACESSAR_CLIENT, exception.getMessage());
            }
        }
        return null;
    }

    public <T, R> T postForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class, uriVariables);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return lerValor(response, javaType);
    }

    public <T, R> T putForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class, uriVariables);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return lerValor(response, javaType);
    }

    public void delete(String url, Object... uriVariables) {
        try {

            restTemplate.delete(url, uriVariables);

        } catch (RestClientException exception) {
            LOGGER.info(exception.getMessage());
        }
    }

    private <T> T lerValor(ResponseEntity<String> response, JavaType javaType) {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.CREATED) {
            try {
                result = objectMapper.readValue(response.getBody(), javaType);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            LOGGER.info(NENHUM_REGISTRO_ENCONTRADO, response.getStatusCode());
        }
        return result;
    }

    private HttpHeaders getHttpHeaders(String authHeader) {
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", authHeader);
        return headers;
    }


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
