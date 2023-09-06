package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByPriceBetween(double low,double high);
}
