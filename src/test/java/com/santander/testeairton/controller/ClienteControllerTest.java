package com.santander.testeairton.controller;

import com.santander.testeairton.controller.request.ClienteRequest;
import com.santander.testeairton.controller.response.ClienteResponse;
import com.santander.testeairton.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClienteControllerTest {

    @Mock private ClienteService clienteService;

    @InjectMocks private ClienteController clienteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSalvarCliente() {
        ClienteRequest clienteRequest = new ClienteRequest();
        ClienteResponse clienteResponse = new ClienteResponse();

        when(clienteService.salvarCliente(clienteRequest)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> response = clienteController.salvarCliente(clienteRequest);

        assertEquals(clienteResponse, response.getBody());

        verify(clienteService, times(1)).salvarCliente(clienteRequest);
    }

    @Test
    public void testListarClientes() {
        Pageable pageable = Pageable.unpaged();
        List<ClienteResponse> clientes = new ArrayList<>();
        Page<ClienteResponse> clientePage = new PageImpl<>(clientes);

        when(clienteService.listarClientes(pageable)).thenReturn(clientePage);

        ResponseEntity<Page<ClienteResponse>> response = clienteController.listarClientes(pageable);

        assertEquals(clientePage, response.getBody());

        verify(clienteService, times(1)).listarClientes(pageable);
    }
}
