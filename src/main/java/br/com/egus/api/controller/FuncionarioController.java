package br.com.egus.api.controller;

import br.com.egus.api.dto.FuncionarioRequest;
import br.com.egus.api.model.pessoa.Cargo;
import br.com.egus.api.model.pessoa.Funcionario;
import br.com.egus.api.repository.FuncionarioRepository;
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

    public FuncionarioController(FuncionarioRepository funcionarioRepository, SenhaService senhaService) {
        this.funcionarioRepository = funcionarioRepository;
        this.senhaService = senhaService;
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(request.getNome());
        funcionario.setEmail(request.getEmail());
        funcionario.setSenha(senhaService.gerarHash(request.getSenha())); // hash da senha
        funcionario.setAtivo(request.getAtivo());
        funcionario.setCargo(Cargo.valueOf(request.getCargo())); // transforma string em enum
        funcionario.setIdMercado(request.getIdMercado());

        Funcionario salvo = funcionarioRepository.save(funcionario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos(@RequestParam int idMercado) {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(
            @PathVariable Long id,
            @RequestBody FuncionarioRequest request) {

        return funcionarioRepository.findById(id).map(funcionario -> {
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
