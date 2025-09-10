package br.com.egus.api.repository;


import br.com.egus.api.model.pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCpf(Long cpf);

    boolean existsByEmail(String email);
    boolean existsByCpf(Long cpf);
}