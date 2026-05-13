package com.fda.model;

public class Menu {
private int menuid;
private int restaurantid;
private String itemName;
private String description;
private double price;
private float rating;
private String isAvailable;
private String imagePath;

public Menu()
{
	
}

public Menu(int menuid, int restaurantid, String itemName, String description, double price, float rating,
		String isAvailable, String imagePath) {
	super();
	this.menuid = menuid;
	this.restaurantid = restaurantid;
	this.itemName = itemName;
	this.description = description;
	this.price = price;
	this.rating = rating;
	this.isAvailable = isAvailable;
	this.imagePath = imagePath;
}

public int getMenuid() {
	return menuid;
}

public void setMenuid(int menuid) {
	this.menuid = menuid;
}

public int getRestaurantid() {
	return restaurantid;
}

public void setRestaurantid(int restaurantid) {
	this.restaurantid = restaurantid;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public float getRating() {
	return rating;
}

public void setRating(float rating) {
	this.rating = rating;
}

public String getIsAvailable() {
	return isAvailable;
}

public void setIsAvailable(String isAvailable) {
	this.isAvailable = isAvailable;
}

public String getImagePath() {
	return imagePath;
}

public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}





}
