package aula.microservicos.configcliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/config")
public class ConfigCliente {

    @Value("${mensagem}")
    String mensagem;

    @GetMapping
    public ResponseEntity<String> mensagem() {

        return new ResponseEntity<String>(mensagem, HttpStatus.OK);
    }

}
