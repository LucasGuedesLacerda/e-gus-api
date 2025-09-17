package br.com.egus.api.service;

import br.com.egus.api.dto.LoginRequest;
import br.com.egus.api.dto.LoginResponse;
import br.com.egus.api.model.pessoa.Funcionario;
import br.com.egus.api.repository.FuncionarioRepository;
import br.com.egus.api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AutenticaService {

    private final FuncionarioRepository funcionarioRepository;
    private final SenhaService senhaService;
    private final UsuarioRepository usuarioRepository;

    public AutenticaService(FuncionarioRepository funcionarioRepository, UsuarioRepository usuarioRepository, SenhaService senhaService) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.senhaService = senhaService;
    }

    public LoginResponse login(LoginRequest request) {
        if (request.getCpf() != null) {
            var usuario = usuarioRepository.findByCpf(request.getCpf())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "CPF ou senha inválidos"));

            if (!senhaService.validarSenha(request.getSenha(), usuario.getSenha())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "CPF ou senha inválidos");
            }

            return new LoginResponse(usuario.getId(), usuario.getNome(), "USUARIO", usuario.getCpf(), usuario.getEmail(), usuario.getPreferences());
        }

        if (request.getEmail() != null) {
            var funcionarioOpt = funcionarioRepository.findByEmail(request.getEmail());

            if (funcionarioOpt.isPresent()) {
                var funcionario = funcionarioOpt.get();

                if (!Boolean.TRUE.equals(funcionario.getAtivo())) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Funcionario inativo");
                }

                if (!senhaService.validarSenha(request.getSenha(), funcionario.getSenha())) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos");
                }

                return new LoginResponse(
                        funcionario.getId(),
                        funcionario.getNome(),
                        "FUNCIONARIO",
                        funcionario.getCargo().name(),
                        funcionario.getIdMercado()
                );
            }

            var usuario = usuarioRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"));

            if (!senhaService.validarSenha(request.getSenha(), usuario.getSenha())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos");
            }

            return new LoginResponse(
                                    usuario.getId(), 
                                    usuario.getNome(), "USUARIO",
                                    usuario.getCpf(),
                                    usuario.getEmail(),
                                    usuario.getPreferences()
                                    );
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário informar email ou CPF");
    }
}
