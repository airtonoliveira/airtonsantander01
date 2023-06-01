package com.santander.testeairton.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;

    private String nome;

    private Boolean exclusive;

    private BigDecimal saldo;

    private String numeroConta;

    private LocalDate dataNascimento;
}
