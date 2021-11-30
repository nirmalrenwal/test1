package com.assigment.rds.dto;

import java.util.List;

import com.assigment.rds.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CatalogResponse extends Response {

	List<Product> products;

	Long count;

	public CatalogResponse(List<Product> products, boolean success) {

		this.products = products;
		super.success = success;
	}

	public CatalogResponse(boolean success) {

		super.success = success;
	}

	public CatalogResponse(Long count, boolean success) {

		this.count = count;
		super.success = success;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
