package com.maiconspas;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Content-Type");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
