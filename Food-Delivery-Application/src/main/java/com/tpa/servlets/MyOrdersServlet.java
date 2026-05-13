package com.tpa.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fda.dao.OrdersDAO;
import com.fda.dao.OrderItemDAO;
import com.fda.daoimpl.OrdersDAOImpl;
import com.fda.daoimpl.OrderItemDAOImpl;
import com.fda.model.Orders;
import com.fda.model.Orderitem;
import com.fda.model.User;

@WebServlet("/myorders")
public class MyOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            response.sendRedirect("login.html");
            return;
        }

        OrdersDAO ordersDAO = new OrdersDAOImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        // Get all orders for this user
        List<Orders> orders = ordersDAO.getOrdersByUserId(user.getUserid());
        Map<Integer, List<Orderitem>> orderItemsMap = new HashMap<>();
        for (Orders order : orders) {
            orderItemsMap.put(order.getOrderid(), orderItemDAO.getOrderItemsByOrderId(order.getOrderid()));
        }

        request.setAttribute("orders", orders);
        request.setAttribute("orderItemsMap", orderItemsMap);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
}
