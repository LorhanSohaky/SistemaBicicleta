package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class QueroBikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueroBikeApplication.class, args);
	}

}
