package com.santander.testeairton.exception.handler;

import com.santander.testeairton.exception.ClienteNaoEncontradoException;
import com.santander.testeairton.exception.SaldoInsuficienteException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class SantanderExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String erros =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(s -> s.getDefaultMessage() + " ")
                        .collect(Collectors.joining());

        HttpErrorResponse error =
                new HttpErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        erros,
                        new Date().getTime(),
                        null,
                        request.getContextPath());

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String erros =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(s -> s.getDefaultMessage() + " ")
                        .collect(Collectors.joining());

        HttpErrorResponse error =
                new HttpErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        erros,
                        new Date().getTime(),
                        null,
                        request.getContextPath());

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<HttpErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException e, HttpServletRequest request) {
        HttpErrorResponse error =
                new HttpErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "Violação de não duplicidade para chave primária (id) ou index único (número da conta).",
                        new Date().getTime(),
                        e.getMessage(),
                        request.getRequestURI());
        return status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<HttpErrorResponse> handleSaldoInsuficienteException(
            SaldoInsuficienteException e, HttpServletRequest request) {
        HttpErrorResponse error =
                new HttpErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        new Date().getTime(),
                        null,
                        request.getRequestURI());
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<HttpErrorResponse> handleClienteNaoEncontradoException(
            ClienteNaoEncontradoException e, HttpServletRequest request) {
        HttpErrorResponse error =
                new HttpErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        new Date().getTime(),
                        null,
                        request.getRequestURI());
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
