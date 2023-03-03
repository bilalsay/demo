package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

public class Product {

	private String name;
	private BigDecimal price;
	private ProductType productType;
	private List<ProductImages> images;

	public Product(String name, BigDecimal price, ProductType productType, List<ProductImages> images) {
		this.name = name;
		this.price = price;
		this.productType = productType;
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<ProductImages> getImages() {
		return images;
	}

	public void setImages(List<ProductImages> images) {
		this.images = images;
	}

}
