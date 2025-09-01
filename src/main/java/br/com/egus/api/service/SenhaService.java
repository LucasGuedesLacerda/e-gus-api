package br.com.egus.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String gerarHash(String senhaEmTexto) {
        return encoder.encode(senhaEmTexto);
    }

    public boolean validarSenha(String senhaEmTexto, String hashSalva) {
        return encoder.matches(senhaEmTexto, hashSalva);
    }
}
