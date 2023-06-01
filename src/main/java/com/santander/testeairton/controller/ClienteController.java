package com.santander.testeairton.controller;

import com.santander.testeairton.controller.doc.ClienteControllerDocumentation;
import com.santander.testeairton.controller.request.ClienteRequest;
import com.santander.testeairton.controller.response.ClienteResponse;
import com.santander.testeairton.service.ClienteService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/santander/cliente")
@RequiredArgsConstructor
public class ClienteController implements ClienteControllerDocumentation {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> salvarCliente(
            @Valid @RequestBody ClienteRequest cliente) {
        ClienteResponse clienteDTO = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> listarClientes(
            @Schema(hidden = true) @PageableDefault(sort = "nome", direction = Sort.Direction.ASC)
                    Pageable pageable) {
        Page<ClienteResponse> clientes = clienteService.listarClientes(pageable);
        return ResponseEntity.ok(clientes);
    }
}
