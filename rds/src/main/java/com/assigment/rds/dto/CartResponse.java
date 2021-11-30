package com.assigment.rds.dto;

import java.util.List;

import com.assigment.rds.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class CartResponse extends Response {

	List<Product> products;

	Integer totalCost;
	
	public CartResponse(List<Product> products,boolean success) {
		
		this.products = products;
		super.success = success;
	}
	public CartResponse(boolean success) {
		
		super.success = success;
	}
	

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
