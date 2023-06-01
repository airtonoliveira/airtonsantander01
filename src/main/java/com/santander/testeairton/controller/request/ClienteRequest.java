package com.santander.testeairton.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "nome é um parâmetro obrigatório.")
    private String nome;

    @NotNull(message = "exclusive é um parâmetro obrigatório.")
    private Boolean exclusive;

    @NotNull(message = "saldo é um parâmetro obrigatório.")
    @DecimalMin(value = "0.0", message = "saldo é precisa ser maior que zero.")
    private BigDecimal saldo;

    @NotBlank(message = "numeroConta é um parâmetro obrigatório.")
    private String numeroConta;

    @NotNull(message = "dataNascimento é um parâmetro obrigatório.")
    private LocalDate dataNascimento;
}
