package com.tpa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fda.daoimpl.*;
import com.fda.model.User;
@WebServlet("/userlogin")
public class UserValidation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String usernameOrEmail = req.getParameter("usernameOrEmail");
        String password = req.getParameter("password");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        UserDAOImpl dao = new UserDAOImpl();

        if (dao.validUser(usernameOrEmail, password)) {
            
            User user = dao.getUserByUsernameOrEmail(usernameOrEmail);

            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);

            // redirect to home
            res.sendRedirect("home");
        } else {
            out.println("<h3 style='color:red; text-align:center;'>Invalid username or password!</h3>");
        }


       
    }
}
