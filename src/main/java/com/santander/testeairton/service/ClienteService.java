package com.santander.testeairton.service;

import com.santander.testeairton.controller.request.ClienteRequest;
import com.santander.testeairton.controller.response.ClienteResponse;
import com.santander.testeairton.domain.Cliente;
import com.santander.testeairton.exception.ClienteNaoEncontradoException;
import com.santander.testeairton.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    public ClienteResponse salvarCliente(ClienteRequest clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente.setSaldo(cliente.getSaldo().setScale(2, RoundingMode.HALF_EVEN));
        return modelMapper.map(clienteRepository.save(cliente), ClienteResponse.class);
    }

    public ClienteRequest atualizarCliente(ClienteRequest clienteDTO, Long id)
            throws ClienteNaoEncontradoException {
        Cliente clienteDB =
                clienteRepository.findById(id).orElseThrow(ClienteNaoEncontradoException::new);
        atualizaDadosDoCliente(clienteDTO, clienteDB);
        clienteRepository.save(clienteDB);
        return modelMapper.map(clienteDB, ClienteRequest.class);
    }

    public Page<ClienteResponse> listarClientes(Pageable pageable) {
        return clienteRepository
                .findAll(pageable)
                .map(cliente -> modelMapper.map(cliente, ClienteResponse.class));
    }

    private static void atualizaDadosDoCliente(ClienteRequest clienteDTO, Cliente clienteDB) {
        clienteDB.setNome(clienteDTO.getNome());
        clienteDB.setExclusive(clienteDTO.getExclusive());
        clienteDB.setDataNascimento(clienteDTO.getDataNascimento());
        clienteDB.setNumeroConta(clienteDTO.getNumeroConta());
        // Saldo está bloqueado para alteração.
    }
}
