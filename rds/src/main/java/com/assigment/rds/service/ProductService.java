package com.assigment.rds.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.rds.entity.Product;
import com.assigment.rds.repository.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	ProductRepository productRepository;
	
	
	public Optional<Product> getProduct(String id){
		return productRepository.findById(id);
	}
	
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public void saveProducts(List<Product> products) {
		productRepository.saveAll(products);
	}
	
	public List<Product> getAllProdcuts(){
		return productRepository.findAll();
	}

}
