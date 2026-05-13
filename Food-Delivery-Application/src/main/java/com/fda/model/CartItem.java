package com.fda.model;

public class CartItem {
	private int id;
	private String name;
	private double price;
	private int quantity;
	private String imagePath; 

	public CartItem() {}

	public CartItem(int id, String name, double price, int quantity, String imagePath) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imagePath = imagePath;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }

	public String getImagePath() { return imagePath; }
	public void setImagePath(String imagePath) { this.imagePath = imagePath; }

	public double getTotalPrice() {
		return price * quantity;
	}
}
