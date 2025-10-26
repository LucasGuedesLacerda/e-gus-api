package br.com.egus.api.controller;

import br.com.egus.api.dto.FuncionarioRequest;
import br.com.egus.api.model.pessoa.Cargo;
import br.com.egus.api.model.pessoa.Funcionario;
import br.com.egus.api.repository.FuncionarioRepository;
import br.com.egus.api.service.EmailValidationService;
import br.com.egus.api.service.SenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;
    private final SenhaService senhaService;
    private final EmailValidationService emailValidationService;

    public FuncionarioController(FuncionarioRepository funcionarioRepository,
                                 SenhaService senhaService,
                                 EmailValidationService emailValidationService) {
        this.funcionarioRepository = funcionarioRepository;
        this.senhaService = senhaService;
        this.emailValidationService = emailValidationService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody FuncionarioRequest request) {
        // Valida e-mail único entre usuários E funcionários
        if (emailValidationService.emailJaExiste(request.getEmail())) {
            return ResponseEntity.badRequest().body("e-mail já existente");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.getNome());
        funcionario.setEmail(request.getEmail());
        funcionario.setSenha(senhaService.gerarHash(request.getSenha()));
        funcionario.setAtivo(request.getAtivo());
        funcionario.setCargo(Cargo.valueOf(request.getCargo()));
        funcionario.setIdMercado(request.getIdMercado());

        Funcionario salvo = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos(@RequestParam int idMercado) {
        List<Funcionario> funcionarios = funcionarioRepository.findByIdMercado(idMercado);
        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody FuncionarioRequest request) {

        return funcionarioRepository.findById(id).map(funcionario -> {
            // Valida e-mail apenas se for diferente do atual
            if (!funcionario.getEmail().equals(request.getEmail())) {
                if (emailValidationService.emailJaExisteExceto(request.getEmail(), id, "FUNCIONARIO")) {
                    return ResponseEntity.badRequest().body("e-mail já existente");
                }
            }

            funcionario.setNome(request.getNome());
            funcionario.setEmail(request.getEmail());
            if (request.getSenha() != null && !request.getSenha().isBlank()) {
                funcionario.setSenha(senhaService.gerarHash(request.getSenha()));
            }
            funcionario.setAtivo(request.getAtivo());
            funcionario.setCargo(Cargo.valueOf(request.getCargo()));
            funcionario.setIdMercado(request.getIdMercado());

            Funcionario atualizado = funcionarioRepository.save(funcionario);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}