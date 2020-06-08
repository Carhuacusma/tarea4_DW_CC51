package web.tarea.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KaoriSnippetApplication {

	public static void main(String[] args) {
		SpringApplication.run(KaoriSnippetApplication.class, args);
	}

}
