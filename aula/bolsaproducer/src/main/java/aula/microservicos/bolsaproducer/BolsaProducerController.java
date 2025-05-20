package aula.microservicos.bolsaproducer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/bolsa")
public class BolsaProducerController {

    @Autowired
    BolsaProducerService service;

    @PostMapping
    public ResponseEntity<String> postMethodName(@RequestBody Map<String, Object> dados) {

        final String mensagem = dados.get("acao") + ":" + dados.get("valor");
        service.send(mensagem);
        return new ResponseEntity<String>(HttpStatus.OK);

    }

}
