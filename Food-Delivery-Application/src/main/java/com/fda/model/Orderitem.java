package com.fda.model;

public class Orderitem {
    private int orderItemid;
    private int orderid;
    private int menuid;
    private int quantity;
    private double totalPrice;

    public Orderitem() {}

    public Orderitem(int orderItemid, int orderid, int menuid, int quantity, double totalPrice) {
        this.orderItemid = orderItemid;
        this.orderid = orderid;
        this.menuid = menuid;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderItemid() { return orderItemid; }
    public void setOrderItemid(int orderItemid) { this.orderItemid = orderItemid; }

    public int getOrderid() { return orderid; }
    public void setOrderid(int orderid) { this.orderid = orderid; }

    public int getMenuid() { return menuid; }
    public void setMenuid(int menuid) { this.menuid = menuid; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
