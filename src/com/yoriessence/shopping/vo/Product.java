package com.yoriessence.shopping.vo;

public class Product {
	
	private int productNo;
	private int stock;
	private int price;
	private String explanation;
	private String productName;
	private String productImage;
	private int productshopify;
	private String productkategorie;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int productNo, int stock, int price, String explanation, String productName,
			String productImage, int productshopify, String productkategorie) {
		super();
		this.productNo = productNo;
		this.stock = stock;
		this.price = price;
		this.explanation = explanation;
		this.productName = productName;
		this.productImage = productImage;
		this.productshopify = productshopify;
		this.productkategorie = productkategorie;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getProductshopify() {
		return productshopify;
	}

	public void setProductshopify(int productshopify) {
		this.productshopify = productshopify;
	}

	public String getProductkategorie() {
		return productkategorie;
	}

	public void setProductkategorie(String productkategorie) {
		this.productkategorie = productkategorie;
	}
	
	
	
	
	
	
}