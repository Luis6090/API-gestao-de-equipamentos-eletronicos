package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            return usuarioRepository.findByEmail(email).get();
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
//        return usuarioRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}