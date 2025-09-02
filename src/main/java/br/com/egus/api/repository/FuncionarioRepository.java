package br.com.egus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.egus.api.model.pessoa.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
     List<Funcionario> findByIdMercado(int idMercado);

    Optional<Funcionario> findByEmail(String email);

}