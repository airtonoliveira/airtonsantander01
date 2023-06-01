package com.santander.testeairton.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SaqueUtil {

    public static final double TAXA_FAIXA_1 = 0.004;
    public static final double TAXA_FAIXA_2 = 0.01;
    private static final BigDecimal VALOR_FAIXA_1 = BigDecimal.valueOf(100);
    private static final BigDecimal VALOR_FAIXA_2 = BigDecimal.valueOf(300);

    public static BigDecimal getTaxaAdministrativa(BigDecimal valor, Boolean isExclusive) {
        if (isIsento(valor, isExclusive)) {
            return BigDecimal.ZERO;
        } else if (valor.compareTo(VALOR_FAIXA_2) <= 0) {
            return BigDecimal.valueOf(TAXA_FAIXA_1);
        } else {
            return BigDecimal.valueOf(TAXA_FAIXA_2);
        }
    }

    private static boolean isIsento(BigDecimal valor, Boolean isExclusive) {
        return valor.compareTo(VALOR_FAIXA_1) <= 0 || isExclusive;
    }
}
