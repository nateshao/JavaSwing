package com.nateshao.login.test;

import com.nateshao.login.test.ui.Layout;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(LoginApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run();
		//SpringApplication.run(RestApiApplication.class, args);
		Layout layout = new Layout();
		layout.start();
	}

}
