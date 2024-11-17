package br.com.devchampions.backendapp.security.login.service;

import br.com.devchampions.backendapp.security.login.UsuariodDetail;
import br.com.devchampions.backendapp.security.login.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailService implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;


    public UsuarioDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(username)
                .map(UsuariodDetail::new).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
