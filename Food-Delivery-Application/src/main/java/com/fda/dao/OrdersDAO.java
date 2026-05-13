package com.fda.dao;

import com.fda.model.Orders;
import java.util.List;

public interface OrdersDAO {
    int addOrder(Orders order);          // returns generated orderId
    Orders getOrderById(int orderId);
    List<Orders> getAllOrders();
    boolean updateOrderStatus(int orderId, String status);
    boolean deleteOrder(int orderId);
	List<Orders> getOrdersByUserId(int userId);
}
