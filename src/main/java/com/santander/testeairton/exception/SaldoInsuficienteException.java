package com.santander.testeairton.exception;

public class SaldoInsuficienteException extends SantanderException {
    public SaldoInsuficienteException() {
        super("Saldo Insuficiente - Não foi possível concluir a operação.", null);
    }
}
