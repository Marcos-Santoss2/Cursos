package br.com.marcosRp.Educ.repository;

import br.com.marcosRp.Educ.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String name);

}
