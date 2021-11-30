package com.assigment.rds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.rds.dto.Cart;
import com.assigment.rds.dto.CartResponse;
import com.assigment.rds.entity.Product;
import com.assigment.rds.exception.CartIsEmptyException;
import com.assigment.rds.exception.ProductAlreadyExists;
import com.assigment.rds.exception.ProductNotFound;

@Service
public class CartService {

	Cart cart;

	@Autowired
	ProductService productService;

	@PostConstruct
	private void init() {

		cart = new Cart();
		cart.setProducts(new ArrayList<Product>());
	}

	public CartResponse getCart() {

		CartResponse cartResponse = new CartResponse(cart.getProducts(), true);
		Integer total = cart.getProducts().stream().map(p -> p.getPrice()).reduce(0, Integer::sum);
		cartResponse.setTotalCost(total);
		return cartResponse;

	}
	

	public CartResponse getItemFromCart(String id) {
			

		List<Product> products = new ArrayList<>();
		Optional<Product> product = cart.getProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
		if(product.isPresent()) {
			products.add(product.get());
		}
		else {
			throw new ProductNotFound("Product is not aviable");
		}
		CartResponse cartResponse = new CartResponse(products, true);
		return cartResponse;

	}

	public void addProductToCart(String id) {

		Optional<Product> product = productService.getProduct(id);
		boolean isExists = cart.getProducts().stream().anyMatch(p -> p.getId().equals(id));
		if (isExists) {
			throw new ProductAlreadyExists("product already in cart");
		}
		if (product.isPresent() && product.get().getQuantity() == 1) {

			Product prd = product.get();
			

			cart.getProducts().add(prd);
			prd.setQuantity(0);
			productService.saveProduct(prd);
		}

		else {
			throw new ProductNotFound("Product is not Avaiable");
		}

	}

	public void deleteProductToCart(String id) {

		boolean isExists = cart.getProducts().removeIf(p -> p.getId().equals(id));

		if (!isExists) {

			throw new ProductNotFound("Product is not Avaiable");
		} else {

			Optional<Product> product = productService.getProduct(id);
			Product prd = product.get();
			prd.setQuantity(1);
			productService.saveProduct(prd);
		}

	}
	
	
	public CartResponse checkout() {
		
		if(cart.getProducts().size() == 0) {
			
			throw new CartIsEmptyException("cart is empty");
		}
		
		CartResponse cartResponse = getCart();
		
		init();
		return cartResponse;
	}
	
	

}
