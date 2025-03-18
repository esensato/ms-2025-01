package aula.microservicos.faculdade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FaculdadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaculdadeApplication.class, args);
	}

}
