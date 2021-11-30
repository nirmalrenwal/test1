package com.assigment.rds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.rds.entity.Product;

@Service
public class CatalogService {
	
	
	@Autowired
	ProductService productService;
	
	
	
	public Long getSize() {
		
		return productService.getAllProdcuts().stream().filter(p -> p.getQuantity() != 0).count();
	}
	
	public List<Product> getProductById(String id){
		
		List<Product> products = new ArrayList<>();
		Optional<Product> product = productService.getProduct(id);
		if(product.isPresent()) {
			products.add(product.get());
		}
		
		return products;
		
		
	}
	
	
	

}
