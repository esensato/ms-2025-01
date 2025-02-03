package aula.microservicos.kafkaproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aula.microservicos.Acao;

@RestController
public class BolsaKafkaController {

    private static final Logger log = LoggerFactory.getLogger(BolsaKafkaController.class);

    @Autowired
    private BolsaKafkaProducer producer;

    @PostMapping("/producer")
    public ResponseEntity<Acao> testePost(@RequestBody Acao req) {

        return new ResponseEntity<Acao>(req, HttpStatus.OK);
    }

    @GetMapping("/producer/ping")
    public ResponseEntity<String> ping() {

        return new ResponseEntity<String>("PONG", HttpStatus.OK);
    }

    @GetMapping("/producer/v1/{sigla}/{valor}")
    public ResponseEntity<String> producer(@PathVariable String sigla, @PathVariable double valor) {
        // envia para o topico uma mensagem
        Acao a = new Acao();
        a.sigla = sigla;
        a.valor = valor;
        producer.send(a);

        log.info("/producer/v1/{sigla}/{valor}");
        return new ResponseEntity<String>("/producer/v1/{sigla}/{valor}", HttpStatus.OK);
    }

    @GetMapping("/producer/v2/{sigla}/{valor}/{data}")
    public ResponseEntity<String> producer(@PathVariable String sigla, @PathVariable double valor,
            @PathVariable double data) {
        // envia para o topico uma mensagem
        Acao a = new Acao();
        a.sigla = sigla;
        a.valor = valor;
        producer.send(a);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(value = "/producer/{sigla}/{valor}", headers = "API-VERSION=3")
    public ResponseEntity<String> producerHeader(@PathVariable String sigla, @PathVariable double valor) {
        // envia para o topico uma mensagem
        Acao a = new Acao();
        a.sigla = sigla;
        a.valor = valor;
        producer.send(a);
        return new ResponseEntity<String>("API-VERSION=3", HttpStatus.OK);
    }
}