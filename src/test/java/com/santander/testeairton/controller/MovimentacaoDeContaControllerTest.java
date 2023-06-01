package com.santander.testeairton.controller;

import com.santander.testeairton.controller.request.MovimentacaoDTO;
import com.santander.testeairton.controller.response.MovimentacaoDeContaResponse;
import com.santander.testeairton.controller.response.TransacaoRealizadaResponse;
import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import com.santander.testeairton.service.MovimentacaoContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovimentacaoDeContaControllerTest {

    @Mock private MovimentacaoContaService movimentacaoContaService;

    @InjectMocks private MovimentacaoDeContaController movimentacaoDeContaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeposito() {
        Long id = 1L;
        MovimentacaoDTO depositoDTO = new MovimentacaoDTO();
        TransacaoRealizadaResponse transacaoRealizadaResponse = new TransacaoRealizadaResponse();

        when(movimentacaoContaService.efetuarDeposito(id, depositoDTO.getValor()))
                .thenReturn(transacaoRealizadaResponse);

        ResponseEntity<TransacaoRealizadaResponse> response =
                movimentacaoDeContaController.deposito(id, depositoDTO);

        assertEquals(transacaoRealizadaResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        verify(movimentacaoContaService, times(1)).efetuarDeposito(id, depositoDTO.getValor());
    }

    @Test
    public void testSaque() {
        Long id = 1L;
        MovimentacaoDTO saqueDTO = new MovimentacaoDTO();
        TransacaoRealizadaResponse transacaoRealizadaResponse = new TransacaoRealizadaResponse();

        when(movimentacaoContaService.efetuarSaque(id, saqueDTO.getValor()))
                .thenReturn(transacaoRealizadaResponse);

        ResponseEntity<TransacaoRealizadaResponse> response =
                movimentacaoDeContaController.saque(id, saqueDTO);

        assertEquals(transacaoRealizadaResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        verify(movimentacaoContaService, times(1)).efetuarSaque(id, saqueDTO.getValor());
    }

    @Test
    public void testConsultarMovimentacoes() {
        Long id = 1L;
        LocalDate dataInicio = LocalDate.now().minusDays(10);
        LocalDate dataFim = LocalDate.now();
        TipoMovimentacaoEnum tipoMovimentacao = TipoMovimentacaoEnum.DEPOSITO;
        Pageable pageable = Pageable.unpaged();
        List<MovimentacaoDeContaResponse> movimentacoes = new ArrayList<>();

        Page<MovimentacaoDeContaResponse> movimentacoesPage = new PageImpl<>(movimentacoes);

        when(movimentacaoContaService.consultarMovimentacoes(
                        id, dataInicio, dataFim, tipoMovimentacao, pageable))
                .thenReturn(movimentacoesPage);

        ResponseEntity<Page<MovimentacaoDeContaResponse>> response =
                movimentacaoDeContaController.consultarMovimentacoes(
                        id, dataInicio, dataFim, tipoMovimentacao, pageable);

        assertEquals(movimentacoesPage, response.getBody());
        assertEquals(200, response.getStatusCodeValue());

        verify(movimentacaoContaService, times(1))
                .consultarMovimentacoes(id, dataInicio, dataFim, tipoMovimentacao, pageable);
    }
}
