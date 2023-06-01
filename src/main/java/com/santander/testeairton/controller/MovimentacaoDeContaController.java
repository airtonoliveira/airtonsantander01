package com.santander.testeairton.controller;

import com.santander.testeairton.controller.doc.MovimentacaoDeContaControllerDocumentation;
import com.santander.testeairton.controller.request.MovimentacaoDTO;
import com.santander.testeairton.controller.response.MovimentacaoDeContaResponse;
import com.santander.testeairton.controller.response.TransacaoRealizadaResponse;
import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import com.santander.testeairton.service.MovimentacaoContaService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/santander/cliente/{id}/movimentacao")
@RequiredArgsConstructor
public class MovimentacaoDeContaController implements MovimentacaoDeContaControllerDocumentation {

    private final MovimentacaoContaService movimentacaoContaService;

    @PostMapping("/deposito")
    public ResponseEntity<TransacaoRealizadaResponse> deposito(
            @PathVariable Long id, @Valid @RequestBody MovimentacaoDTO depositoDTO) {
        TransacaoRealizadaResponse movimentacao =
                movimentacaoContaService.efetuarDeposito(id, depositoDTO.getValor());
        return ResponseEntity.ok(movimentacao);
    }

    @PostMapping("/saque")
    public ResponseEntity<TransacaoRealizadaResponse> saque(
            @PathVariable Long id, @Valid @RequestBody MovimentacaoDTO saqueDTO) {
        TransacaoRealizadaResponse movimentacao =
                movimentacaoContaService.efetuarSaque(id, saqueDTO.getValor());
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping()
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
                    Pageable pageable) {

        if (dataInicio == null) {
            dataInicio = LocalDate.now().minus(15, ChronoUnit.DAYS);
        }

        if (dataFim == null) {
            dataFim = LocalDate.now();
        }

        return ResponseEntity.ok(
                movimentacaoContaService.consultarMovimentacoes(
                        id, dataInicio, dataFim, tipoMovimentacao, pageable));
    }
}
