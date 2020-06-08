package web.tarea.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEurekaClient
@SpringBootApplication
public class KaoriServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaoriServicioApplication.class, args);
	}

}
