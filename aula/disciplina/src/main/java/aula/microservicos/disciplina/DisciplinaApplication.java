package aula.microservicos.disciplina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DisciplinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisciplinaApplication.class, args);
	}

}
