package com.sombrainc.shared.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1583487548566287914L;

	private long id;
	private long owner;
	private String productName;
	private String brand;
	private String type;
	private String size;
	private String color;
	private int year;
	private double price;
	private String description;
	private int imageNumber;
	
	public Product() {}

	public Product(long owner, String productName, String brand,
			String type, String size, String color, int year, double price,
			String description, int imageNumber) {
		super();
		this.owner = owner;
		this.productName = productName;
		this.brand = brand;
		this.type = type;
		this.size = size;
		this.color = color;
		this.year = year;
		this.price = price;
		this.description = description;
		this.imageNumber = imageNumber;
	}

	public Product(long id, long owner, String productName, String brand,
			String type, String size, String color, int year, double price,
			String description, int imageNumber) {
		super();
		this.id = id;
		this.owner = owner;
		this.productName = productName;
		this.brand = brand;
		this.type = type;
		this.size = size;
		this.color = color;
		this.year = year;
		this.price = price;
		this.description = description;
		this.imageNumber = imageNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", owner=" + owner + ", productName="
				+ productName + ", brand=" + brand + ", type=" + type
				+ ", size=" + size + ", color=" + color + ", year=" + year
				+ ", price=" + price + ", description=" + description
				+ ", imageNumber=" + imageNumber + "]";
	}
}