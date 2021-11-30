package com.assigment.rds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.rds.entity.Product;
import com.assigment.rds.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(){
		
		return new ResponseEntity<List<Product>>(productService.getAllProdcuts(), HttpStatus.OK);
		
	}

}
