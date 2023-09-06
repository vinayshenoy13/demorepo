package com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.model.Product;
import com.security.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getProductBetween(double low , double high) {
		List<Product> productBetween = productRepository.findByPriceBetween(low, high);
		return productBetween;
	}
}
