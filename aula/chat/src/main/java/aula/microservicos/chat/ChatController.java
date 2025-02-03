package aula.microservicos.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/mensagens") // direciona para os inscritos no tópico
    public Mensagem getMensagens(Mensagem mensagem) {
        return mensagem;
    }

}