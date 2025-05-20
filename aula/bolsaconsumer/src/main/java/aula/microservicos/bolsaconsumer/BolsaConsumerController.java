package aula.microservicos.bolsaconsumer;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bolsa")
public class BolsaConsumerController {

    @Autowired
    BolsaConsumerService service;

    @GetMapping
    public ResponseEntity<Set<String>> getAcoes() {
        return new ResponseEntity<Set<String>>(service.getAcoes(), HttpStatus.OK);
    }

}
