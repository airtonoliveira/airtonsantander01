package com.santander.testeairton.controller.doc;

import com.santander.testeairton.controller.request.MovimentacaoDTO;
import com.santander.testeairton.controller.response.MovimentacaoDeContaResponse;
import com.santander.testeairton.controller.response.TransacaoRealizadaResponse;
import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import com.santander.testeairton.exception.handler.HttpErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.time.LocalDate;

public interface MovimentacaoDeContaControllerDocumentation {

    @Operation(description = "Realiza o saque de um valor do saldo do cliente.")
    @ApiResponse(responseCode = "200", description = "Saque realizado com sucesso.")
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida.",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema =
                                    @Schema(
                                            type = "HttpErrorResponse",
                                            implementation = HttpErrorResponse.class)))
    @ApiResponse(
            responseCode = "500",
            description = "Erro inesperado.",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema =
                                    @Schema(
                                            type = "HttpErrorResponse",
                                            implementation = HttpErrorResponse.class)))
    @PostMapping("/saque")
    public ResponseEntity<TransacaoRealizadaResponse> saque(
            @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Código do Cliente",
                            content =
                                    @Content(
                                            schema =
                                                    @Schema(
                                                            type = "integer",
                                                            defaultValue = "10000")))
                    @PathVariable
                    Long id,
            @Valid @RequestBody MovimentacaoDTO saqueDTO);

    @Operation(description = "Realiza o saque de um valor do saldo do cliente.")
    @ApiResponse(responseCode = "200", description = "Saque realizado com sucesso.")
    @ApiResponse(
            responseCode = "400",
            description = "Requisição inválida.",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema =
                                    @Schema(
                                            type = "HttpErrorResponse",
                                            implementation = HttpErrorResponse.class)))
    @ApiResponse(
            responseCode = "500",
            description = "Erro inesperado.",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema =
                                    @Schema(
                                            type = "HttpErrorResponse",
                                            implementation = HttpErrorResponse.class)))
    @PostMapping("/deposito")
    public ResponseEntity<TransacaoRealizadaResponse> deposito(
            @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "Código do Cliente",
                            content =
                                    @Content(
                                            schema =
                                                    @Schema(
                                                            type = "integer",
                                                            defaultValue = "10000")))
                    @PathVariable
                    Long id,
            @Valid @RequestBody MovimentacaoDTO depositoDTO);

    @Operation(
            description = "Consulta as Movimentações por Cliente.",
            parameters = {
                @Parameter(
                        in = ParameterIn.QUERY,
                        description = "Página (0..N)",
                        name = "page",
                        content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                @Parameter(
                        in = ParameterIn.QUERY,
                        description = "Número de registro por página.",
                        name = "size",
                        content =
                                @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                @Parameter(
                        in = ParameterIn.QUERY,
                        description = "Ordenação dos registros(<CAMPO>,asc|desc).",
                        name = "sort",
                        content =
                                @Content(
                                        array =
                                                @ArraySchema(
                                                        schema =
                                                                @Schema(
                                                                        type = "string",
                                                                        defaultValue =
                                                                                "nome,asc"))))
            })
    @GetMapping
    public ResponseEntity<Page<MovimentacaoDeContaResponse>> consultarMovimentacoes(
            @PathVariable Long id,
            @RequestParam(name = "dataInicio", required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate dataInicio,
            @RequestParam(name = "dataFim", required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate dataFim,
            @RequestParam(name = "tipoMovimentacao", required = false)
                    TipoMovimentacaoEnum tipoMovimentacao,
            @Schema(hidden = true) @PageableDefault(sort = "data", direction = Sort.Direction.ASC)
                    Pageable pageable);
}
