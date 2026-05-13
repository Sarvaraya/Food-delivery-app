package com.fda.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.fda.dao.UseDAO;
import com.fda.model.User;
import com.fda.dbconnection.Dbconnection;

public class UserDAOImpl implements UseDAO {

    private static final String INSERT_USER_SQL =
        "INSERT INTO user (name, username, password, email, phone, createdDate) VALUES (?, ?, ?, ?, ?, NOW())";

    private static final String UPDATE_USER_SQL =
        "UPDATE user SET name=?, password=?, email=?, phone=? WHERE userId=?";

    private static final String DELETE_USER_SQL =
        "DELETE FROM user WHERE userId=?";

    private static final String GET_USER_BY_ID_SQL =
        "SELECT * FROM user WHERE userId=?";

    private static final String GET_ALL_USERS_SQL =
        "SELECT * FROM user";

    private static final String LOGIN_USER_SQL =
        "SELECT * FROM user WHERE (username=? OR email=?) AND password=?";

    @Override
    public boolean registerUser(User user) {
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_USER_SQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_USER_SQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setInt(5, user.getUserid());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_USER_SQL)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(int userId) {
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_USER_BY_ID_SQL)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUser(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_USERS_SQL)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractUser(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // returns User object if found, or null
    @Override
    public boolean validUser(String usernameOrEmail, String password) {
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(LOGIN_USER_SQL)) {

            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);
            ps.setString(3, password);
            System.out.println("Trying login for: " + usernameOrEmail + " / " + password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // returns boolean based on loginUser
    

    // helper to check metadata presence of column
    private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int cols = md.getColumnCount();
        for (int i = 1; i <= cols; i++) {
            if (md.getColumnLabel(i).equalsIgnoreCase(columnName)) return true;
        }
        return false;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        // use metadata check to avoid "Column not found" exceptions if your DB doesn't have some fields
        if (hasColumn(rs, "userId")) {
            try { user.setUserid(rs.getInt("userId")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "name")) {
            try { user.setName(rs.getString("name")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "username")) {
            try { user.setUsername(rs.getString("username")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "password")) {
            try { user.setPassword(rs.getString("password")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "email")) {
            try { user.setEmail(rs.getString("email")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "phone")) {
            try { user.setPhone(rs.getString("phone")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "createdDate")) {
            try { user.setCreatedDate(rs.getTimestamp("createdDate")); } catch(Exception ignored) {}
        }
        if (hasColumn(rs, "lastLoginDate")) {
            try { user.setLastLoginDate(rs.getTimestamp("lastLoginDate")); } catch(Exception ignored) {}
        }
        return user;
    }
    @Override
    public User getUserByUsernameOrEmail(String usernameOrEmail) {
        User user = null;
        String sql = "SELECT * FROM user WHERE username=? OR email=?";
        try (Connection con = Dbconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserid(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setCreatedDate(rs.getTimestamp("createdDate"));
                user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {
        // you can forward to registerUser or implement differently
        return registerUser(user);
    }

	
}
