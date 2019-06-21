package lin.louis.poc.benchmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class BenchmarkApp {

	public static void main(String[] args) {
		SpringApplication.run(BenchmarkApp.class, args);
	}
}
