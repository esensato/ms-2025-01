package aula.microservicos.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityCustomUserDetails implements UserDetailsService {

    @Autowired
    private SecurityRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // verifica na base de usuarios se o usuario existe
        if (repo.findById(username).isEmpty())
            throw new UsernameNotFoundException(username);

        UsuarioEntity usuario = repo.findById(username).get();
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

        for (String role : usuario.getRoles().split("\\;")) {
            System.out.println("ROLE --> " + role);
            auth.add(new SimpleGrantedAuthority(role));
        }

        return new User(usuario.getEmail(), usuario.getSenha(), auth);
    }

}
