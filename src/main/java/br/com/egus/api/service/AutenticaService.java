package br.com.egus.api.service;

import br.com.egus.api.dto.LoginRequest;
import br.com.egus.api.dto.LoginResponse;
import br.com.egus.api.model.pessoa.Funcionario;
import br.com.egus.api.repository.FuncionarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AutenticaService {

    private final FuncionarioRepository funcionarioRepository;
    private final SenhaService senhaService;

    public AutenticaService(FuncionarioRepository funcionarioRepository, SenhaService senhaService) {
        this.funcionarioRepository = funcionarioRepository;
        this.senhaService = senhaService;
    }

    public LoginResponse login(LoginRequest request) {
        Funcionario funcionario = funcionarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"));

        if (!Boolean.TRUE.equals(funcionario.getAtivo())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário inativo");
        }

        if (!senhaService.validarSenha(request.getSenha(), funcionario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos");
        }

        return new LoginResponse(funcionario.getId(), funcionario.getCargo().name());
    }
}
