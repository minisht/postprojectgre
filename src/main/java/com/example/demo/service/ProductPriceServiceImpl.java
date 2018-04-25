package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.ProductServiceException;
import com.example.demo.repository.ProductPrice;
import com.example.demo.repository.ProductPriceRepository;
import com.example.demo.utils.DataSourceUtils;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	ProductPriceRepository prodPrcRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${datajson.url}")
	String url;
	
	@Value("${datajson.path.title}")
	String jsonPath;
	

	@Override
	public ProductPrice getProductByID(Integer id) {

		String urlVal = restTemplate.getForObject(url, String.class);
		String name = DataSourceUtils.executeJSONPath(urlVal, jsonPath);
		ProductPrice obj = prodPrcRepo.findByProductID(id);
		if(obj == null) {
			throw new ProductServiceException("ProductID not found " + id, HttpStatus.NOT_FOUND); 
		}
		obj.setProductTitle(name);
		return obj;

	}

	@Override
	public ProductPrice updateProduct(Integer id, ProductPrice body) {

		ProductPrice p = prodPrcRepo.findByProductID(id);
		if (p != null) {
			p.setCurrencyCode(body.getCurrencyCode());
			p.setCurrentPrice(body.getCurrentPrice());
			return prodPrcRepo.save(p);
		}
		return null;
	}

	@Override
	public ProductPrice insertProduct(ProductPrice body) {

		return prodPrcRepo.save(body);
	}

	@Override
	public int deleteProduct(Integer id) {

		ProductPrice p = prodPrcRepo.findByProductID(id);
		if (p != null) {
			prodPrcRepo.delete(p.getId());
			return 1;
		}
		return 0;
	}

}
