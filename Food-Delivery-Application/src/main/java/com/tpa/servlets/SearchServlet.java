package com.tpa.servlets;

import java.io.IOException;
import java.util.List;

import com.fda.dao.RestaurantDAO;
import com.fda.daoimpl.RestaurantDAOImpl;
import com.fda.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> restaurants = keyword == null || keyword.isBlank()
            ? restaurantDAO.getAllRestaurants()
            : restaurantDAO.searchRestaurant(keyword);

        request.setAttribute("restaurant", restaurants);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
