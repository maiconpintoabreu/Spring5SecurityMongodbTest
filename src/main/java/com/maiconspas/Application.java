package com.maiconspas;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.maiconspas.entity.User;
import com.maiconspas.repository.UserRepository;

/**
 * @author Maicon Santana
 *
 */
@SpringBootApplication
@PropertySource(value = { "classpath:application.properties","classpath:application.prod.properties" })
public class Application implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> user = repository.findByUserName("admin");
		if(!user.isPresent()) {
			User admin = new User("Admin","admin");
			admin.setPassword("admin");
			repository.save(admin);
		}
	}
}
