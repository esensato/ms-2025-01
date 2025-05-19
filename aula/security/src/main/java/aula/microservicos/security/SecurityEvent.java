package aula.microservicos.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEvent {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent<UsuarioEntity> evento) {

        System.out.println("Problemas de autorizacao: " + evento.getAuthentication().get().getName());

    }
}
