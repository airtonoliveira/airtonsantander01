package com.santander.testeairton.controller.doc;

import com.santander.testeairton.controller.request.ClienteRequest;
import com.santander.testeairton.controller.response.ClienteResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ClienteControllerDocumentation {

    @Operation(description = "Salva o cliente na base da dados.")
    @ApiResponse(responseCode = "200", description = "Cliente salvo com sucesso.")
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
    @PostMapping
    ResponseEntity<ClienteResponse> salvarCliente(@Valid @RequestBody ClienteRequest cliente);

    @Operation(
            description = "Consulta todos clientes na base da dados.",
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
    public ResponseEntity<Page<ClienteResponse>> listarClientes(
            @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable);
}
