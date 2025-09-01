package br.com.egus.api.controller;

import br.com.egus.api.dto.LoginRequest;
import br.com.egus.api.dto.LoginResponse;
import br.com.egus.api.service.AutenticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticaController {

    private final AutenticaService autenticaService;

    public AutenticaController(AutenticaService autenticaService) {
        this.autenticaService = autenticaService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(autenticaService.login(request));
    }
}
