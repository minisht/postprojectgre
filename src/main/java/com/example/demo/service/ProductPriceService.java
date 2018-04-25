package com.example.demo.service;

import com.example.demo.repository.ProductPrice;

public interface ProductPriceService {
	
    ProductPrice getProductByID(Integer id);
	ProductPrice updateProduct(Integer id, ProductPrice body);
	ProductPrice insertProduct(ProductPrice body);
	int deleteProduct(Integer id);

}
