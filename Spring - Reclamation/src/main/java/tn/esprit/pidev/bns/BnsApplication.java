package tn.esprit.pidev.bns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@Configuration

public class BnsApplication {

	public static void main(String[] args) {



		SpringApplication.run(BnsApplication.class, args);
	}

}
