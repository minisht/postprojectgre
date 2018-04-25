package com.example.demo.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.repository.ProductPrice;
import com.example.demo.service.ProductPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductPriceControllerTest {
	
	@Mock
	ProductPriceService prodPrcServ;
	
	MockMvc mockMvc;
	
	@InjectMocks
	ProductPriceController prodPriceController;
	
	ProductPrice pp = new ProductPrice();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		prodPriceController = new ProductPriceController();
		prodPriceController.proPrcService = prodPrcServ;
		mockMvc = MockMvcBuilders.standaloneSetup(prodPriceController).build();
		
	}

	@Test
	public void getProductByIDTest() throws Exception {
		Mockito.when(prodPrcServ.getProductByID(123)).thenReturn(pp);
		mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}", 123))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
				
	}
	
	@Test
	public void updateProductTest() throws Exception {
		pp.setProductID(123);
		pp.setCurrencyCode("USD");
		pp.setCurrentPrice(10.00);
		
		Mockito.when(prodPrcServ.updateProduct(123, pp)).thenReturn(pp);
		mockMvc.perform(MockMvcRequestBuilders.put("/product/{id}", 123).content(new ObjectMapper().writeValueAsString(pp)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
//		.andExpect(MockMvcResultMatchers.content().bytes(new ObjectMapper().writeValueAsString(pp).getBytes()));
		
	}
	
	@Test
	public void insertProduct() throws Exception {
		Mockito.when(prodPrcServ.insertProduct(pp)).thenReturn(pp);
		mockMvc.perform(MockMvcRequestBuilders.post("/product").content(new ObjectMapper().writeValueAsString(pp)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void deleteProduct() throws Exception {
		Mockito.when(prodPrcServ.deleteProduct(123)).thenReturn(1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/product/{id}", 123))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
//		try {
//		throw new RuntimeException("Some ex");
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
	}

}
