package com.santander.testeairton.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpErrorResponse {

    private int status;
    private String mensagem;
    private long timestamp;
    private String mensagemDesenvolvedor;
    private String uriPatch;
}
