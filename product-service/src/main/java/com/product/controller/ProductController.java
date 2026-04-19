package com.product.controller;

import java.util.List;

import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/product")
@OpenAPIDefinition(
		  info = @Info(title = "My Product", version = "1.0", description = "API documentation")
		)
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@GetMapping("/")
	public List<Product> getAllProducts(){
		return productRepository.findAll();		
	}
	
	@PostMapping("/")
	public void  addProduct(@RequestBody Product prod){	
		productRepository.save(prod);
	}
	@PutMapping("/")
	public void  editProduct(@RequestBody Product prod){	
		productRepository.save(prod);
	}

}
