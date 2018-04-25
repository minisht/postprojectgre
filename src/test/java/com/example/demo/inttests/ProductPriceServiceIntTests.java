package com.example.demo.inttests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.repository.ProductPrice;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class ProductPriceServiceIntTests {
	
	@Autowired
	TestRestTemplate template;
	
	@Test
	public void runGetTest() {
		
		ResponseEntity<String> getResponse = template.getForEntity("/product/123", String.class);
		Assert.assertEquals(getResponse.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void runPostTest() {
		
		ProductPrice pp = new ProductPrice();
		pp.setCurrencyCode("USD");
		pp.setCurrentPrice(10.10);
		pp.setProductID(27367);
		
		HttpEntity<ProductPrice> httpEntity = new HttpEntity<ProductPrice>(pp);
		ResponseEntity<ProductPrice> postResponse = template.postForEntity("/product", httpEntity, ProductPrice.class);
		Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void runPutTest() {
		ProductPrice pp = new ProductPrice();
		pp.setCurrencyCode("USD");
		pp.setCurrentPrice(10.10);
		pp.setProductID(27367);
		
		HttpHeaders head = new HttpHeaders();
		head.add("test", "test");
		
		HttpEntity<ProductPrice> httpEntity = new HttpEntity<ProductPrice>(pp, head);
		ResponseEntity<ProductPrice> putResponse = template.exchange("/product/123", HttpMethod.PUT, httpEntity, ProductPrice.class);
		Assert.assertEquals(putResponse.getStatusCode(), HttpStatus.OK);
	}
	
//	@Test
//	public void runDeleteTest() {
//		ProductPrice pp = new ProductPrice();
//		pp.setCurrencyCode("USD");
//		pp.setCurrentPrice(10.10);
//		pp.setProductID(27367);
//		
//		HttpHeaders head = new HttpHeaders();
//		head.add("test", "test");
//		
//		HttpEntity<ProductPrice> httpEntity = new HttpEntity<ProductPrice>(pp, head);
//		ResponseEntity<ProductPrice> deleteResponse = template.exchange("/product/123", HttpMethod.DELETE, httpEntity, ProductPrice.class);
//		Assert.assertEquals(deleteResponse.getStatusCode(), HttpStatus.OK);
//		
//	}
	

}
