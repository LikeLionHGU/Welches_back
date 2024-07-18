package likelion.summer.welches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication

public class WelchesApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelchesApplication.class, args);
	}
}
