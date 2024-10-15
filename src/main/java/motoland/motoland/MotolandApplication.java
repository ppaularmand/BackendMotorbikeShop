package motoland.motoland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"motoland.motoland.repositories", "motoland.motoland.service","motoland.motoland.model","motoland.motoland.controllers"}) // Ensure this is correct
@EntityScan("motoland.motoland.model") // Make sure this covers your entity locations
@EnableJpaRepositories("motoland.motoland.repositories")
public class MotolandApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotolandApplication.class, args);
	}
}