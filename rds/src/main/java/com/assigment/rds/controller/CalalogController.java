package com.assigment.rds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.rds.dto.CatalogResponse;
import com.assigment.rds.entity.Product;
import com.assigment.rds.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CalalogController {

	@Autowired
	CatalogService catalogService;

	@GetMapping("/size")
	public ResponseEntity<CatalogResponse> getSize() {
		try {
			Long size = catalogService.getSize();
			return new ResponseEntity<CatalogResponse>(new CatalogResponse(size, true), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<CatalogResponse>(HttpStatus.NOT_IMPLEMENTED);
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CatalogResponse> getProduct(@PathVariable("id") String id) {
		try {
			List<Product> products = catalogService.getProductById(id);
			if(products.size() > 0)
			return new ResponseEntity<CatalogResponse>(new CatalogResponse(products, true), HttpStatus.OK);
			else {
				return new ResponseEntity<CatalogResponse>(new CatalogResponse(false),HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {

			return new ResponseEntity<CatalogResponse>(new CatalogResponse(false),HttpStatus.NOT_FOUND);
		}

	}

}
