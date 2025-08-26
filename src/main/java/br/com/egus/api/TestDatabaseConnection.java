package br.com.egus.api;

import br.com.egus.api.model.pessoa.Funcionario;
import br.com.egus.api.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestDatabaseConnection {

    @Bean
    CommandLineRunner run(FuncionarioRepository repository) {
        return args -> {
            System.out.println("test Supabase...");
            repository.findAll().forEach(func -> {
                System.out.println("Funcionário encontrado: " + func.getNome() + " | Email: " + func.getEmail()+" | Cargo: " + func.getCargo() + " | ID Mercado: " + func.getIdMercado());
            });
        };
    }
}
