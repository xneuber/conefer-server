package br.edu.ifg.manutencao.coneferserver.exception;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InfoMessage {

    private int status;

    private String mensagem;
}
