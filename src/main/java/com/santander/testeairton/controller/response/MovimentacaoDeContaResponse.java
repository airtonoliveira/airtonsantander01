package com.santander.testeairton.controller.response;

import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentacaoDeContaResponse {

    private String clienteNumeroConta;

    private TipoMovimentacaoEnum tipo;

    private BigDecimal valor;

    private LocalDateTime data;

    private BigDecimal percentualTaxa;

    private BigDecimal valorTaxa;
}
