package com.fda.daoimpl;

import com.fda.dao.RestaurantDAO;
import com.fda.model.Restaurant;
import com.fda.model.User;
import com.fda.dbconnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final String INSERT_QUERY =
            "INSERT INTO restaurant (name, address, phone, rating, cusineType, isActive, eta, adminUserid, imagePath) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_BY_ID =
            "SELECT * FROM restaurant WHERE restaurantid = ?";

    private static final String GET_ALL =
            "SELECT * FROM restaurant WHERE isActive='yes'"; // fetch only active restaurants

    private static final String UPDATE_QUERY =
            "UPDATE restaurant SET name=?, address=?, phone=?, rating=?, cusineType=?, isActive=?, eta=?, adminUserid=?, imagePath=? " +
            "WHERE restaurantid=?";

    private static final String DELETE_QUERY =
            "DELETE FROM restaurant WHERE restaurantid=?";

    @Override
    public void createRestaurant(Restaurant restaurant) {
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_QUERY)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setLong(3, restaurant.getPhone());
            ps.setFloat(4, restaurant.getRating());
            ps.setString(5, restaurant.getCusineType());
            ps.setString(6, restaurant.getIsActive());
            ps.setTime(7, restaurant.getEta());
            ps.setInt(8, restaurant.getAdminUserid());
            ps.setString(9, restaurant.getImagePath());

            ps.executeUpdate();
            System.out.println("Restaurant added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_BY_ID)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                restaurant = mapResultSetToRestaurant(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                restaurants.add(mapResultSetToRestaurant(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setLong(3, restaurant.getPhone());
            ps.setFloat(4, restaurant.getRating());
            ps.setString(5, restaurant.getCusineType());
            ps.setString(6, restaurant.getIsActive());
            ps.setTime(7, restaurant.getEta());
            ps.setInt(8, restaurant.getAdminUserid());
            ps.setString(9, restaurant.getImagePath());
            ps.setInt(10, restaurant.getRestaurantid());

            ps.executeUpdate();
            System.out.println("Restaurant updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (Connection conn = Dbconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_QUERY)) {

            ps.setInt(1, restaurantId);
            ps.executeUpdate();
            System.out.println("Restaurant deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Restaurant mapResultSetToRestaurant(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantid(rs.getInt("restaurantid"));
        restaurant.setName(rs.getString("name"));
        restaurant.setAddress(rs.getString("address"));
        restaurant.setPhone(rs.getLong("phone"));
        restaurant.setRating(rs.getFloat("rating"));
        restaurant.setCusineType(rs.getString("cusineType"));
        restaurant.setIsActive(rs.getString("isActive"));
        restaurant.setEta(rs.getTime("eta"));
        restaurant.setAdminUserid(rs.getInt("adminUserid"));
        restaurant.setImagePath(rs.getString("imagePath"));
        return restaurant;
    }

    

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserid(rs.getInt("userId"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setCreatedDate(rs.getTimestamp("createdDate"));
        user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));
        return user;
    }
    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        List<Restaurant> restaurants = new ArrayList<>();

        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
            		 "SELECT * FROM restaurant WHERE LOWER(name) LIKE LOWER(?) OR LOWER(cusineType) LIKE LOWER(?) OR LOWER(address) LIKE LOWER(?)"
             )) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setRestaurantid(rs.getInt("restaurantid"));
                r.setName(rs.getString("name"));
                r.setAddress(rs.getString("address"));
                r.setPhone(rs.getLong("phone"));
                r.setRating(rs.getFloat("rating"));
                r.setCusineType(rs.getString("cusineType"));
                r.setIsActive(rs.getString("isActive"));
                r.setEta(rs.getTime("eta"));
                r.setAdminUserid(rs.getInt("adminUserid"));
                r.setImagePath(rs.getString("imagePath"));
                restaurants.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

}
