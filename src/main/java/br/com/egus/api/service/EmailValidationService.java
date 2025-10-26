package br.com.egus.api.service;

import br.com.egus.api.repository.FuncionarioRepository;
import br.com.egus.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailValidationService {

    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository;

    public EmailValidationService(UsuarioRepository usuarioRepository,
                                  FuncionarioRepository funcionarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    /**
     * Verifica se o e-mail já está cadastrado em qualquer tipo de conta
     * @param email E-mail a ser verificado
     * @return true se o e-mail já existe, false caso contrário
     */
    public boolean emailJaExiste(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return usuarioRepository.existsByEmail(email) ||
                funcionarioRepository.findByEmail(email).isPresent();
    }

    /**
     * Verifica se o e-mail já existe, excluindo um ID específico da validação
     * (útil para atualizações onde o próprio registro pode manter seu e-mail)
     * @param email E-mail a ser verificado
     * @param idExcluir ID a ser excluído da verificação
     * @param tipoExcluir Tipo da conta a excluir ("USUARIO" ou "FUNCIONARIO")
     * @return true se o e-mail já existe em outra conta, false caso contrário
     */
    public boolean emailJaExisteExceto(String email, Long idExcluir, String tipoExcluir) {
        if (email == null || email.isBlank()) {
            return false;
        }

        boolean existeEmUsuario = usuarioRepository.findByEmail(email)
                .map(u -> !("USUARIO".equals(tipoExcluir) && u.getId().equals(idExcluir)))
                .orElse(false);

        boolean existeEmFuncionario = funcionarioRepository.findByEmail(email)
                .map(f -> !("FUNCIONARIO".equals(tipoExcluir) && f.getId().equals(idExcluir)))
                .orElse(false);

        return existeEmUsuario || existeEmFuncionario;
    }
}