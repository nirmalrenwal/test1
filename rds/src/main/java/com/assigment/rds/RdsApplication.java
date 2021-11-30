package com.assigment.rds;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.assigment.rds.entity.Product;
import com.assigment.rds.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class RdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/products.json");
			try {
				List<Product> products = mapper.readValue(inputStream,typeReference);
				productService.saveProducts(products);
				System.out.println("Products Saved!");
			} catch (IOException e){
				System.out.println("Unable to save products: " + e.getMessage());
			}
		};
	}

}
