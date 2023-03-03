package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class Offer {

	private Customer customer;
	private List<Product> products;
	private LocalDateTime offerDate;

	public Offer(Customer customer, List<Product> products, LocalDateTime offerDate) {
		this.customer = customer;
		this.products = products;
		this.offerDate = offerDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public LocalDateTime getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(LocalDateTime offerDate) {
		this.offerDate = offerDate;
	}
}
