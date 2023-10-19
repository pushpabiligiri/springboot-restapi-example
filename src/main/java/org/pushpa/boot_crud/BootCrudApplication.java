package org.pushpa.boot_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info =@Info(contact = @Contact(name = "pushpa"),title = "Spring Boot Crud",description = "Simple Rest API using Spring Boot"))
public class BootCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCrudApplication.class, args);
	}

}


