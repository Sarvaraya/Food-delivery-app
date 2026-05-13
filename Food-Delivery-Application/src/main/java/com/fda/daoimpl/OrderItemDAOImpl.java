package com.fda.daoimpl;

import com.fda.dao.OrderItemDAO;
import com.fda.dbconnection.Dbconnection;
import com.fda.model.Orderitem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    private static final String INSERT_ITEM =
        "INSERT INTO orderitem (orderId, menuId, quantity, totalPrice) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ORDER_ID =
        "SELECT * FROM orderitem WHERE orderId = ?";
    private static final String UPDATE_QUANTITY =
        "UPDATE orderitem SET quantity = ? WHERE orderItemId = ?";
    private static final String DELETE_ITEM =
        "DELETE FROM orderitem WHERE orderItemId = ?";

    @Override
    public void addOrderItem(Orderitem item) {
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ITEM)) {

            ps.setInt(1, item.getOrderid());
            ps.setInt(2, item.getMenuid());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getTotalPrice());

            ps.executeUpdate();
            System.out.println("✅ Order item added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Orderitem> getOrderItemsByOrderId(int orderId) {
        List<Orderitem> list = new ArrayList<>();
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ORDER_ID)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Orderitem item = new Orderitem();
                item.setOrderItemid(rs.getInt("orderItemId"));
                item.setOrderid(rs.getInt("orderId"));
                item.setMenuid(rs.getInt("menuId"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalPrice(rs.getDouble("totalPrice"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateOrderItemQuantity(int orderItemId, int quantity) {
        boolean updated = false;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_QUANTITY)) {

            ps.setInt(1, quantity);
            ps.setInt(2, orderItemId);
            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {
        boolean deleted = false;
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ITEM)) {

            ps.setInt(1, orderItemId);
            deleted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
