package br.edu.ifg.manutencao.coneferserver.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumEstadoCivil {
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    SEPARADO("Separado"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viuvo");

    private final String descricao;
}
