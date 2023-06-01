package com.santander.testeairton.controller.response;

import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
public class TransacaoRealizadaResponse {

    private String mensagem;

    public TransacaoRealizadaResponse(
            TipoMovimentacaoEnum tipo,
            BigDecimal valorTransacao,
            BigDecimal valorFinal,
            BigDecimal taxa) {
        StringBuilder mensagemBuilder = new StringBuilder();
        mensagemBuilder.append(
                String.format(
                        "%s DE R$ %s REALIZADO COM SUCESSO, SALDO ATUAL: R$ %s",
                        tipo.name(), valorTransacao, valorFinal));

        if (taxa.compareTo(BigDecimal.ZERO) > 0) {
            mensagemBuilder.append(
                    String.format(
                            "(TAXA DE ADMINISTRAÇÃO: R$ %s)",
                            taxa.setScale(2, RoundingMode.HALF_EVEN)));
        }

        this.mensagem = mensagemBuilder.toString();
    }
}
