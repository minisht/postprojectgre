package com.example.demo.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.ProductServiceException;
import com.example.demo.repository.ProductPrice;
import com.example.demo.repository.ProductPriceRepository;
import com.example.demo.utils.DataSourceUtils;

import junit.framework.Assert;

public class ProductPriceServiceImplTest {
	
	@Mock
	ProductPriceRepository prodPrcRepo;
	
	@Mock
	RestTemplate restTemplate;
	
	String url;
	
	String jsonPath;
	
	@InjectMocks
	ProductPriceServiceImpl prodPrcSerImpl;
	
	ProductPrice p = new ProductPrice();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		prodPrcSerImpl = new ProductPriceServiceImpl();
		prodPrcSerImpl.prodPrcRepo = prodPrcRepo;
		prodPrcSerImpl.restTemplate = restTemplate;
		prodPrcSerImpl.jsonPath = "$['productid']";
		prodPrcSerImpl.url = "sampleUrl";
	}
	
	
	@Test
	public void getProductByIDTest() {
//		Mockito.doReturn("{\"productid\":\"23178\"}").when(restTemplate).getForObject(Mockito.anyString(), Mockito.any());
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn("{\"productid\":\"23178\"}");
		Mockito.when(prodPrcRepo.findByProductID(123)).thenReturn(p);
		
		ProductPrice pp = prodPrcSerImpl.getProductByID(123);
		Assert.assertEquals(pp.getProductTitle(), p.getProductTitle());
		
	}
	
	@Test(expected=ProductServiceException.class)
	public void getProductByIDTestException() {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn("{\"productid\":\"23178\"}");
		
		prodPrcSerImpl.getProductByID(123);
		
	}
	
	
	@Test
	public void updateProductTest() {
		Mockito.when(prodPrcRepo.findByProductID(123)).thenReturn(p);
		Mockito.when(prodPrcRepo.save(p)).thenReturn(p);
		
		ProductPrice pp = prodPrcSerImpl.updateProduct(123, p);
		Assert.assertNotNull(pp);
	}
	
	@Test
	public void insertProduct() {
		Mockito.when(prodPrcRepo.save(p)).thenReturn(p);
		
		ProductPrice pp = prodPrcSerImpl.insertProduct(p);
		Assert.assertNotNull(pp);
	}
	
	@Test
	public void deleteProductTest() {
		Mockito.when(prodPrcRepo.findByProductID(123)).thenReturn(p);
		Mockito.doNothing().when(prodPrcRepo).delete(p);
		
		int pp = prodPrcSerImpl.deleteProduct(123);
		Assert.assertEquals(pp, 1);
	}

}
