package br.com.egus.api.controller;

import br.com.egus.api.dto.UsuarioRequest;
import br.com.egus.api.model.pessoa.Usuario;
import br.com.egus.api.repository.UsuarioRepository;
import br.com.egus.api.service.SenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final SenhaService senhaService;

    public UsuarioController(UsuarioRepository usuarioRepository, SenhaService senhaService) {
        this.usuarioRepository = usuarioRepository;
        this.senhaService = senhaService;
    }

    // Cadastrar novo usuário
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        if (usuarioRepository.existsByCpf(request.getCpf())) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(senhaService.gerarHash(request.getSenha()));
        usuario.setCpf(request.getCpf());

        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    // Listar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioRequest request) {

        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());

            if (request.getSenha() != null && !request.getSenha().isBlank()) {
                usuario.setSenha(senhaService.gerarHash(request.getSenha()));
            }

            usuario.setCpf(request.getCpf());

            if (request.getPreferences() != null) {
                 usuario.setPreferences(request.getPreferences());
            }
            
            Usuario atualizado = usuarioRepository.save(usuario);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
