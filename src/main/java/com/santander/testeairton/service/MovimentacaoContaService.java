package com.santander.testeairton.service;

import com.santander.testeairton.controller.response.MovimentacaoDeContaResponse;
import com.santander.testeairton.controller.response.TransacaoRealizadaResponse;
import com.santander.testeairton.domain.Cliente;
import com.santander.testeairton.domain.MovimentacaoDeConta;
import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import com.santander.testeairton.exception.ClienteNaoEncontradoException;
import com.santander.testeairton.exception.SaldoInsuficienteException;
import com.santander.testeairton.repository.ClienteRepository;
import com.santander.testeairton.repository.MovimentacaoRepository;
import com.santander.testeairton.util.SaqueUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimentacaoContaService {

    private final ClienteRepository clienteRepository;

    private final MovimentacaoRepository movimentacaoRepository;

    private final ModelMapper modelMapper;

    public TransacaoRealizadaResponse efetuarDeposito(Long id, BigDecimal valor) {
        Cliente cliente =
                clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
        cliente.setSaldo(cliente.getSaldo().add(valor).setScale(2, RoundingMode.HALF_EVEN));
        clienteRepository.save(cliente);
        movimentacaoRepository.save(
                MovimentacaoDeConta.builder()
                        .cliente(cliente)
                        .valor(valor)
                        .tipo(TipoMovimentacaoEnum.DEPOSITO)
                        .data(LocalDateTime.now())
                        .percentualTaxa(BigDecimal.ZERO)
                        .valorTaxa(BigDecimal.ZERO)
                        .build());
        return new TransacaoRealizadaResponse(
                TipoMovimentacaoEnum.DEPOSITO, valor, cliente.getSaldo(), BigDecimal.ZERO);
    }

    public TransacaoRealizadaResponse efetuarSaque(Long id, BigDecimal valor) {
        Cliente cliente =
                clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);

        verificaSaldo(valor, cliente);

        BigDecimal percentualTaxa = SaqueUtil.getTaxaAdministrativa(valor, cliente.getExclusive());

        MovimentacaoDeConta mov = salvarMovimentacao(valor, cliente, percentualTaxa);
        cliente = salvarSaldoCliente(valor, cliente, percentualTaxa);

        return getTransacaoRealizadaResponse(valor, cliente, mov);
    }

    private static void verificaSaldo(BigDecimal valor, Cliente cliente) {
        if (valor.compareTo(cliente.getSaldo()) > 0) {
            throw new SaldoInsuficienteException();
        }
    }

    private static TransacaoRealizadaResponse getTransacaoRealizadaResponse(
            BigDecimal valor, Cliente cliente, MovimentacaoDeConta mov) {
        return new TransacaoRealizadaResponse(
                TipoMovimentacaoEnum.SAQUE, valor, cliente.getSaldo(), mov.getValorTaxa());
    }

    private Cliente salvarSaldoCliente(
            BigDecimal valor, Cliente cliente, BigDecimal percentualTaxa) {
        BigDecimal valorAposTaxa = valor.add(valor.multiply(percentualTaxa));
        cliente.setSaldo(
                cliente.getSaldo().subtract(valorAposTaxa).setScale(2, RoundingMode.HALF_EVEN));
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    private MovimentacaoDeConta salvarMovimentacao(
            BigDecimal valor, Cliente cliente, BigDecimal percentualTaxa) {
        return movimentacaoRepository.save(
                MovimentacaoDeConta.builder()
                        .cliente(cliente)
                        .valor(valor)
                        .tipo(TipoMovimentacaoEnum.SAQUE)
                        .data(LocalDateTime.now())
                        .percentualTaxa(percentualTaxa)
                        .valorTaxa(
                                valor.multiply(percentualTaxa).setScale(2, RoundingMode.HALF_EVEN))
                        .build());
    }

    public Page<MovimentacaoDeContaResponse> consultarMovimentacoes(
            Long idCliente,
            LocalDate dataInicio,
            LocalDate dataFim,
            TipoMovimentacaoEnum tipo,
            Pageable pageable) {
        Page<MovimentacaoDeConta> resultado;
        if (tipo == null) {
            resultado =
                    movimentacaoRepository.findByCliente_IdAndDataBetween(
                            idCliente, startOfDay(dataInicio), endOfDay(dataFim), pageable);
        } else {
            resultado =
                    movimentacaoRepository.findByCliente_IdAndDataBetweenAndTipo(
                            idCliente, startOfDay(dataInicio), endOfDay(dataFim), tipo, pageable);
        }
        return resultado.map(
                movimentacao -> modelMapper.map(movimentacao, MovimentacaoDeContaResponse.class));
    }

    private static LocalDateTime startOfDay(LocalDate dataInicio) {
        return dataInicio.atStartOfDay();
    }

    private static LocalDateTime endOfDay(LocalDate dataFim) {
        return dataFim.atTime(23, 59, 59, 999999999);
    }
}
