package com.santander.testeairton.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO {

    @NotNull(message = "valor é um parâmetro obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "valor precisa ser maior que zero.")
    BigDecimal valor;
}
