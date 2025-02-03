package aula.microservicos.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {

        // define qual será o endpoint para a troca de mensagens
        registry.addEndpoint("/mensagem").withSockJS();

    }

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {

        // prefixo do endpoint onde as mensagens serão recebidas pelos clientes
        registry.enableSimpleBroker("/topic/");

        // prefixo do endpoint para onde as mensagens serão enviadas
        registry.setApplicationDestinationPrefixes("/app");

    }

}