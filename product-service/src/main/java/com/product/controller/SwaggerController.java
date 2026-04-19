package com.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.product.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(
		  info = @Info(title = "My API", version = "1.0", description = "API documentation")
		)
public class SwaggerController {
	
	ConcurrentMap<Integer, Product> myMap=new ConcurrentHashMap<>();

	
	@GetMapping("/")
	public List<Product> getAll() {
		return new ArrayList<Product>(myMap.values());
	}

	//@ApiOperation(value = "Add an product")
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product prod) {
		myMap.put(prod.getId(), prod);
		return prod;
	}

}
