package com.santander.testeairton.exception;

public class ClienteNaoEncontradoException extends SantanderException {
    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado no sistema.", null);
    }
}
