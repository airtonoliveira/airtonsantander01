package com.santander.testeairton.repository;

import com.santander.testeairton.domain.MovimentacaoDeConta;
import com.santander.testeairton.enumerators.TipoMovimentacaoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoDeConta, Long> {
    Page<MovimentacaoDeConta> findByCliente_IdAndDataBetween(
            Long clienteId, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);

    Page<MovimentacaoDeConta> findByCliente_IdAndDataBetweenAndTipo(
            Long clienteId,
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            TipoMovimentacaoEnum tipoMovimentacao,
            Pageable pageable);
}
