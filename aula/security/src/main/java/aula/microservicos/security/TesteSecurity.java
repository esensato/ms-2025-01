package aula.microservicos.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TesteSecurity implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("------> REQUISICAO");

        RestClient restClient = RestClient.create();
        ResponseEntity<String> response = restClient.get()
                .uri("http://localhost:8080/seguranca/admin")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .retrieve()
                .toEntity(String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

    }

}