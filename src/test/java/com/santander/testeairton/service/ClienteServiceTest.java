package com.santander.testeairton.service;

import com.santander.testeairton.controller.request.ClienteRequest;
import com.santander.testeairton.controller.response.ClienteResponse;
import com.santander.testeairton.domain.Cliente;
import com.santander.testeairton.exception.ClienteNaoEncontradoException;
import com.santander.testeairton.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {

    @Mock private ClienteRepository clienteRepository;

    @Mock private ModelMapper modelMapper;

    @InjectMocks private ClienteService clienteService;

    @Test
    void salvarCliente_ShouldReturnClienteResponse() {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNome("Airton");
        clienteRequest.setSaldo(new BigDecimal("1000.00"));

        Cliente cliente = createCliente(null, clienteRequest.getNome(), clienteRequest.getSaldo());

        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(modelMapper.map(clienteRequest, Cliente.class)).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteResponse.class))
                .thenReturn(createClienteResponse(1L, "Airton", new BigDecimal("1000.00")));
        // salvando cliente
        ClienteResponse savedCliente = clienteService.salvarCliente(clienteRequest);

        assertEquals(clienteRequest.getNome(), savedCliente.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void atualizarCliente_WhenClienteDoesNotExist_ShouldThrowClienteNaoEncontradoException() {
        Long id = 10000L;
        ClienteRequest clienteRequest = new ClienteRequest();

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());
        when(modelMapper.map(clienteRequest, Cliente.class)).thenReturn(new Cliente());
        assertThrows(
                ClienteNaoEncontradoException.class,
                () -> clienteService.atualizarCliente(clienteRequest, id));
        verify(clienteRepository, times(1)).findById(id);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void listarClientes_ShouldReturnListOfClienteResponses() {
        Pageable pageable = Pageable.unpaged();
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(createCliente(1000L, "Airton", new BigDecimal("1000.00")));

        Page<Cliente> clientePage = new PageImpl<>(clienteList, pageable, clienteList.size());

        List<ClienteResponse> expectedClienteResponses =
                clienteList.stream()
                        .map(cliente -> modelMapper.map(cliente, ClienteResponse.class))
                        .collect(Collectors.toList());

        when(clienteRepository.findAll(pageable)).thenReturn(clientePage);

        Page<ClienteResponse> clienteResponses = clienteService.listarClientes(pageable);

        assertEquals(expectedClienteResponses, clienteResponses.getContent());
        verify(clienteRepository, times(1)).findAll(pageable);
    }

    private Cliente createCliente(Long id, String nome, BigDecimal saldo) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setSaldo(saldo);
        cliente.setExclusive(Boolean.TRUE);
        cliente.setDataNascimento(LocalDate.now());
        return cliente;
    }

    private ClienteResponse createClienteResponse(Long id, String nome, BigDecimal saldo) {
        ClienteResponse cliente = new ClienteResponse();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setSaldo(saldo);
        cliente.setExclusive(Boolean.TRUE);
        cliente.setDataNascimento(LocalDate.now());
        return cliente;
    }
}
