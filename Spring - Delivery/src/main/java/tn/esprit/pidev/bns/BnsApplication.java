package tn.esprit.pidev.bns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy

public class BnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnsApplication.class, args);
	}

}
