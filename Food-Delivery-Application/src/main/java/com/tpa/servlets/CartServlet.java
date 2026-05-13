package com.tpa.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fda.model.*;
import com.fda.dao.MenuDAO;
import com.fda.daoimpl.MenuDAOImpl;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        int menuId = Integer.parseInt(request.getParameter("itemId"));

        int quantity = 1;
        String restaurantId = request.getParameter("restaurantId");

        // Fetch Menu details from DB using DAO implementation
        MenuDAO menuDAO = new MenuDAOImpl();
        Menu menu = menuDAO.getMenuById(menuId);

        // Fallback if menu not found
        if (menu == null) {
            response.sendRedirect("menu.jsp");
            return;
        }

        //  Create CartItem using Menu details (include imagePath)
        CartItem item = new CartItem();
        item.setId(menu.getMenuid());
        item.setName(menu.getItemName());
        item.setPrice(menu.getPrice());
        item.setQuantity(quantity);
        item.setImagePath(menu.getImagePath());

        //  Perform actions
        if ("add".equalsIgnoreCase(action)) {
            Integer restId = Integer.parseInt(request.getParameter("restaurantId"));
            cart.addCartItem(item, restId);
        } else if ("increase".equalsIgnoreCase(action)) {
            cart.updateCartItem(item, 1);
        } else if ("decrease".equalsIgnoreCase(action)) {
            cart.updateCartItem(item, -1);
        } else if ("remove".equalsIgnoreCase(action)) {
            cart.removeCartItem(item);
        }

        //  Redirect to cart.jsp
        response.sendRedirect("cart.jsp");
    }
}
