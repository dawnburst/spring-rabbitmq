package com.dawn.rabbitmqspringexample;

import com.dawn.rabbitmqspringexample.tutorial1.application.RabbitmqSpringRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitmqSpringExampleApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to " +
					"control its behavior.\n");
			System.out.println("Sample usage: java -jar " +
					"rabbit-tutorials.jar " +
					"--spring.profiles.active=hello-world,sender");
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitmqSpringRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSpringExampleApplication.class, args);
	}

}
