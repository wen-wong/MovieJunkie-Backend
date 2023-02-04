package ca.mcgill.ecse428.moviejunkie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class MovieJunkieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieJunkieApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello World!";
	}

}
