package br.com.devchampions.backendapp.security.login.repository;

import br.com.devchampions.backendapp.security.login.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {


    Optional<Usuario> findByEmail(final String email);


}
