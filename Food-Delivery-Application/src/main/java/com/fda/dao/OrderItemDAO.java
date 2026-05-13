package com.fda.dao;

import com.fda.model.Orderitem;
import java.util.List;

public interface OrderItemDAO {
    void addOrderItem(Orderitem item);
    List<Orderitem> getOrderItemsByOrderId(int orderId);
    boolean updateOrderItemQuantity(int orderItemId, int quantity);
    boolean deleteOrderItem(int orderItemId);
}
