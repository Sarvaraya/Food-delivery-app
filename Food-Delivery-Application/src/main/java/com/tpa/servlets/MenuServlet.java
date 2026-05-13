package com.tpa.servlets;

import com.fda.daoimpl.MenuDAOImpl;
import com.fda.model.Menu;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Step 1: try to get restaurantId from request
        String restIdParam = request.getParameter("restaurantId");

        // Step 2: fallback to session if null
        if ((restIdParam == null || restIdParam.isEmpty()) && session.getAttribute("restaurantId") != null) {
            restIdParam = session.getAttribute("restaurantId").toString();
        }

        // Step 3: if still null → redirect to menu.jsp
        if (restIdParam == null || restIdParam.isEmpty()) {
            response.sendRedirect("menu.jsp");
            return;
        }

        int restaurantId = Integer.parseInt(restIdParam);

        // Save in session for future use
        session.setAttribute("restaurantId", restaurantId);

        // Fetch menu items
        MenuDAOImpl menuDAO = new MenuDAOImpl();
        List<Menu> menuItems = menuDAO.getMenusByRestaurantId(restaurantId);

        request.setAttribute("menuItems", menuItems);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
        dispatcher.forward(request, response);
    }
}
