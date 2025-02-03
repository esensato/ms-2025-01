package aula.microservicos.eurekacliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EurekaClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClienteApplication.class, args);
	}

}
