package aula.microservicos.eurekacliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eureka")
public class EurekaCliente {

    @Autowired
    FaculdadeProxy proxy;

    @GetMapping
    public ResponseEntity<String> ping() {
        return proxy.ping();
    }

}
