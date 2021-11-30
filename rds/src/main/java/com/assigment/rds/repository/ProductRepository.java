package com.assigment.rds.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assigment.rds.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String id);
}