package com.assigment.rds.dto;

import java.util.List;

import com.assigment.rds.entity.Product;

public class Cart {
	
	
	
	List<Product> products;
	
	
	Integer totalCost;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
	
	
	

}
