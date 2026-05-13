package com.fda.daoimpl;
import com.fda.dao.*;
import com.fda.model.*;
import com.fda.dbconnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    private static final String INSERT_QUERY = "INSERT INTO menu (menuId, itemName, description, price, restaurantId) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_MENU_BY_ID = "SELECT * FROM menu WHERE menuId = ?";
    private static final String GET_ALL_MENUS = "SELECT * FROM menu";
    private static final String UPDATE_MENU = "UPDATE menu SET itemName = ?, description = ?, price = ?, restaurantId = ? WHERE menuId = ?";
    private static final String DELETE_MENU = "DELETE FROM menu WHERE menuId = ?";

    @Override
    public void createMenu(Menu menu) {
        try (Connection connection = Dbconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY)) {

            pstmt.setInt(1, menu.getMenuid());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setInt(5, menu.getRestaurantid());

            pstmt.executeUpdate();
            System.out.println("Menu item created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenuById(int menuId) {
        Menu menu = null;
        String query = "SELECT * FROM menu WHERE menuId = ?";

        try (Connection connection = Dbconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                menu = new Menu();
                menu.setMenuid(rs.getInt("menuId"));
                menu.setRestaurantid(rs.getInt("restaurantId"));
                menu.setItemName(rs.getString("itemName")); // ✅ FIXED — was "name"
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getDouble("price"));
                menu.setRating(rs.getFloat("ratings"));
                menu.setIsAvailable(rs.getString("isAvailable"));
                menu.setImagePath(rs.getString("imagePath")); // ✅ include image
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        try (Connection connection = Dbconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(GET_ALL_MENUS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuid(rs.getInt("menuId"));
                menu.setItemName(rs.getString("itemName"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getDouble("price"));
                menu.setRestaurantid(rs.getInt("restaurantId"));
                menus.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public void updateMenu(Menu menu) {
        try (Connection connection = Dbconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(UPDATE_MENU)) {

            pstmt.setString(1, menu.getItemName());
            pstmt.setString(2, menu.getDescription());
            pstmt.setDouble(3, menu.getPrice());
            pstmt.setInt(4, menu.getRestaurantid());
            pstmt.setInt(5, menu.getMenuid());

            pstmt.executeUpdate();
            System.out.println("Menu item updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (Connection connection = Dbconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(DELETE_MENU)) {

            pstmt.setInt(1, menuId);
            pstmt.executeUpdate();
            System.out.println("Menu item deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE restaurantId = ?";

        try (Connection connection = Dbconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuid(rs.getInt("menuId"));
                menu.setRestaurantid(rs.getInt("restaurantId"));
                menu.setItemName(rs.getString("itemName"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getDouble("price"));
                menu.setRating(rs.getFloat("ratings"));
                menu.setIsAvailable(rs.getString("isAvailable"));
                menu.setImagePath(rs.getString("imagePath"));
                menus.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menus;
    }
    @Override
    public List<Menu> searchMenu(String keyword) {
        List<Menu> menuList = new ArrayList<>();

        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
            		 "SELECT * FROM menu WHERE LOWER(itemName) LIKE LOWER(?) OR LOWER(description) LIKE LOWER(?)"
             )) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuid(rs.getInt("menuId"));
                menu.setRestaurantid(rs.getInt("restaurantId"));
                menu.setItemName(rs.getString("itemName"));
                menu.setDescription(rs.getString("description"));
                menu.setPrice(rs.getDouble("price"));
                menu.setRating(rs.getFloat("ratings"));
                menu.setIsAvailable(rs.getString("isAvailable"));
                menu.setImagePath(rs.getString("imagePath"));
                menuList.add(menu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }

    

}
