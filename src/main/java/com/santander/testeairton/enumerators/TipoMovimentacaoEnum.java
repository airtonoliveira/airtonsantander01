package com.santander.testeairton.enumerators;

import java.util.Arrays;

public enum TipoMovimentacaoEnum {
    SAQUE,
    DEPOSITO;

    public static TipoMovimentacaoEnum of(String value) {
        return Arrays.stream(values())
                .filter(enumType -> enumType.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
