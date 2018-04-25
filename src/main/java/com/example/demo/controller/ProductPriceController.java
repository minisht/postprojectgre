package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ProductPrice;
import com.example.demo.service.ProductPriceService;

@RestController
public class ProductPriceController {

	public static Logger log = LoggerFactory.getLogger(ProductPriceController.class);
	
	@Autowired
	ProductPriceService proPrcService;
	
	@GetMapping(path = "/product/{id}")
	public ProductPrice getProductByID(@PathVariable (name = "id") Integer id) {
		
		log.debug("We are getting ProductByID {}",id);
		return proPrcService.getProductByID(id);
	}
	
	@PutMapping(path = "/product/{id}")
	public ProductPrice updateProduct(@PathVariable (name = "id") Integer id, @RequestBody ProductPrice body) {
		
		return proPrcService.updateProduct(id, body);
	}
	
	@PostMapping(path = "/product")
	public ProductPrice insertProduct(@RequestBody ProductPrice body) {
		
		return proPrcService.insertProduct(body);
	}
	
	@DeleteMapping(path = "/product/{id}")
	public int deleteProduct(@PathVariable (name = "id") Integer id) {
		
		return proPrcService.deleteProduct(id);
	}

}
