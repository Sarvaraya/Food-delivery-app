package com.tpa.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.fda.daoimpl.UserDAOImpl;
import com.fda.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userservlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get form data
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address"); // optional

        
        User user = new User(0, name, username, password, email, phone, address, null, null);

        
        UserDAOImpl dao = new UserDAOImpl();
        boolean result = dao.registerUser(user);

        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (result) {
            out.println("<h2 style='color:green;'>Hey " + name + ", Registration Successful!</h2>");
            out.println("<a href='login.html'>Click here to login</a>");
        } else {
            out.println("<h2 style='color:red;'>" + name + ", Something went wrong. Please try again!</h2>");
            out.println("<a href='register.html'>Go back</a>");
        }
    }
}
