package br.com.juridico.totvs.fullstack.Backend.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MelhorEstacao {
    PRIMAVERA,
    VERAO,
    OUTONO,
    INVERNO,
    O_ANO_TODO;

    //permite valores tipo verão
    @JsonCreator
    public static MelhorEstacao from(String value) {
        return MelhorEstacao.valueOf(value.toUpperCase().replace("Ã", "A").replace("É", "E"));
    }
}
