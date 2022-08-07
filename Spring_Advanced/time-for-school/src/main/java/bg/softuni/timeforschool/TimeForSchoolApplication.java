package bg.softuni.timeforschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TimeForSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeForSchoolApplication.class, args);
	}

}
