package com.ifg1.manutencao.coneferserver.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Log4j2
@EnableSwagger2
@EnableWebMvc
@Configuration
public class SwaggerConfig {

    private final ResponseMessage msg201 = simpleMessage(201, "Recurso criado");
    private final ResponseMessage msg204put = simpleMessage(204, "Atualizado com sucesso");
    private final ResponseMessage msg204del = simpleMessage(204, "Excluído com sucesso");
    private final ResponseMessage msg401 = simpleMessage(401, "Você não tem permissão para acessar este recurso");
    private final ResponseMessage msg403 = simpleMessage(403, "Você não tem permissão para acessar este recurso");
    private final ResponseMessage msg404 = simpleMessage(404, "Recurso não encontrado.");
    private final ResponseMessage msg422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage msg500 = simpleMessage(500, "Foi gerada uma exceção no servidor");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(msg403, msg401, msg404, msg500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(msg201, msg401, msg403, msg422, msg500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(msg204put, msg401, msg403, msg404, msg422, msg500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(msg204del, msg401, msg403, msg404, msg500))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ifg1.manutencao.coneferserver"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        log.trace("Retornando Builder de Informações da API");
        return new ApiInfoBuilder().title("Conefer-Server API")
                .title("Conefer-Server API - Backend da aplicação")
                .description("API para gestão de controle de compras a pagar, receber e estoque.")
                .termsOfServiceUrl("http://springfox.io")
                .contact(new Contact("Acadêmico IFG", "https://www.ifg.edu.br/", "xneuber@gmail.com"))
                .version("1.0.0")
                .build();
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

}
