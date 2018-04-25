package com.example.demo.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="\"ProductPrice\"") // Enclosing the table name in \" \", because it's a mixed case matching
@Entity
public class ProductPrice {
	
	@Id
	@SequenceGenerator(name="ProductPrice_ID_seq", sequenceName = "\"ProductPrice_ID_seq\"")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ProductPrice_ID_seq")
	@Column(name="\"ID\"")
	private Integer id;

	
	@Column(name="\"ProductID\"")
	private long productID;
	
	@Column(name="\"Current_Price\"")
	private double currentPrice;
	
	@Column(name="\"Currency_Code\"")
	private String currencyCode;
	
	@Transient // This property gets ignored while doing insert/update operations
	private String productTitle;

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	

}
