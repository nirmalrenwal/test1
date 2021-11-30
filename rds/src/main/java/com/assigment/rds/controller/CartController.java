package com.assigment.rds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.rds.dto.CartResponse;
import com.assigment.rds.exception.ProductNotFound;
import com.assigment.rds.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	
	
	@GetMapping
	public ResponseEntity<CartResponse> getCart(){
		
		try {
			return  new ResponseEntity<CartResponse>(cartService.getCart(),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<CartResponse>(new CartResponse(false),HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<CartResponse> getItemInCart(@PathVariable("id") String id){
		
		try {
			return  new ResponseEntity<CartResponse>(cartService.getItemFromCart(id),HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<CartResponse>(new CartResponse(false),HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/checkout")
	public ResponseEntity<CartResponse> checkout() {

		try {

			return new ResponseEntity<CartResponse>(cartService.checkout(), HttpStatus.OK);
		}

		catch (Exception e) {
			return new ResponseEntity<CartResponse>(new CartResponse(false),HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<CartResponse> deleteFromCart(@PathVariable("id") String id) {

		try {
					cartService.deleteProductToCart(id);
			return new ResponseEntity<CartResponse>(new CartResponse(true), HttpStatus.OK);
		}

		catch (Exception e) {
			return new ResponseEntity<CartResponse>(new CartResponse(false),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/item/{id}")
	public ResponseEntity<CartResponse> addToCart(@PathVariable("id") String id) {

		try {
					cartService.addProductToCart(id);
			return new ResponseEntity<CartResponse>(new CartResponse(true), HttpStatus.OK);
		}

		catch (ProductNotFound e) {
			return new ResponseEntity<CartResponse>(new CartResponse(false),HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return new ResponseEntity<CartResponse>(new CartResponse(false),HttpStatus.NOT_IMPLEMENTED);
		}
	}

}
