package spring.consertaaqui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"spring.consertaaqui.model"})
@ComponentScan(basePackages = {"spring.consertaaqui.*"})
@EnableJpaRepositories(basePackages = {"spring.consertaaqui.repository"})
public class ConsertaAquiDeveloperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsertaAquiDeveloperApplication.class, args);
	}

}
