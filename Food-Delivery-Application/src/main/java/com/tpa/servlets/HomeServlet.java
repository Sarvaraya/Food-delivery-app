package com.tpa.servlets;

import com.fda.dao.RestaurantDAO;
import com.fda.daoimpl.*;
import com.fda.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/home") 
public class HomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Create DAO instance
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();

        // Fetch all restaurants from the database
        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
        Collections.shuffle(restaurants);

        // Add data to request scope
        request.setAttribute("restaurant", restaurants);

        // Forward to home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
