package com.fda.daoimpl;

import com.fda.dao.OrdersDAO;
import com.fda.dbconnection.Dbconnection;
import com.fda.model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    private static final String INSERT_ORDER = 
        "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = 
        "SELECT * FROM orders WHERE orderId = ?";
    private static final String SELECT_ALL = 
        "SELECT * FROM orders";
    private static final String UPDATE_STATUS = 
        "UPDATE orders SET status = ? WHERE orderId = ?";
    private static final String DELETE_ORDER = 
        "DELETE FROM orders WHERE orderId = ?";

    @Override
    public int addOrder(Orders order) {
        int generatedId = -1;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserid());
            ps.setInt(2, order.getRestaurantid());
            ps.setTimestamp(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());
            ps.setString(6, order.getPaymentMode());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public Orders getOrderById(int orderId) {
        Orders order = null;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                order = new Orders();
                order.setOrderid(rs.getInt("orderId"));
                order.setUserid(rs.getInt("userId"));
                order.setRestaurantid(rs.getInt("restaurantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> list = new ArrayList<>();
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Orders order = new Orders();
                order.setOrderid(rs.getInt("orderId"));
                order.setUserid(rs.getInt("userId"));
                order.setRestaurantid(rs.getInt("restaurantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));
                list.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        boolean updated = false;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_STATUS)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        boolean deleted = false;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ORDER)) {

            ps.setInt(1, orderId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
    @Override
    public List<Orders> getOrdersByUserId(int userId) {
        List<Orders> list = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE userId = ? ORDER BY orderDate DESC";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Orders order = new Orders();
                order.setOrderid(rs.getInt("orderId"));
                order.setUserid(rs.getInt("userId"));
                order.setRestaurantid(rs.getInt("restaurantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
