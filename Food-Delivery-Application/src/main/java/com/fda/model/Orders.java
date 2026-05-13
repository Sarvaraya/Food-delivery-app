package com.fda.model;

import java.sql.Timestamp;

public class Orders {
    private int orderid;
    private int userid;
    private int restaurantid;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private String paymentMode;

    public Orders() {}

    public Orders(int orderid, int userid, int restaurantid, Timestamp orderDate,
                  double totalAmount, String status, String paymentMode) {
        this.orderid = orderid;
        this.userid = userid;
        this.restaurantid = restaurantid;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
        
        
        
    }

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public  void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

    
}
