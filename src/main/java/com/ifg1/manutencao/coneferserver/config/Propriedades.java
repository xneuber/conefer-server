package com.ifg1.manutencao.coneferserver.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@ToString
@Validated
@Configuration
@ConfigurationProperties(prefix = "conefer")
public class Propriedades {

    private List<String> origensPermitida;

    private String basePackageEnum;

    private String urlPortal;
}
