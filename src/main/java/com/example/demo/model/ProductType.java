package com.example.demo.model;

public enum ProductType {

	Main(1), Sub(2);

	private int value;

	private ProductType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
