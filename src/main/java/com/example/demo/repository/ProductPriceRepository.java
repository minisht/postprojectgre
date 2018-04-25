package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>{
	
//	@Query(value = "SELECT * FROM \"ProductPrice\" WHERE \"ProductID\" = ?1", nativeQuery=true)
//	ProductPrice findByProductID(@Param("ProductID") long productID); 
	
	ProductPrice findByProductID(long productID);
	
//	ProductPrice findByProductIDAndCurrencyCode(long productID, String currencyCode);
//	
//	@Query("SELECT productID, COUNT(productID) FROM ProductPrice GROUP BY productID")
//	List<String> getProductCount();
	

}
