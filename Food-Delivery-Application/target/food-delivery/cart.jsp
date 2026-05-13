<%@ page import="java.util.*, com.fda.model.Cart, com.fda.model.CartItem" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Your Cart | UrbanEats</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
    }

    /* ===== HEADER ===== */
    header {
        position: sticky;
        top: 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px 40px;
        background: #2c3e50;
        color: white;
        z-index: 100;
        box-shadow: 0 2px 6px rgba(0,0,0,0.2);
    }

    header h1 {
        cursor: pointer;
        font-size: 28px;
        margin: 0;
    }

    .nav-buttons {
        display: flex;
        align-items: center;
        gap: 15px;
    }

    .dropdown {
        position: relative;
    }

    .dropbtn {
        background: #34495e;
        padding: 8px 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        color: white;
        transition: 0.3s;
    }

    .dropbtn:hover {
        background: #3d5a73;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        top: 100%;
        left: 0;
        background: white;
        min-width: 140px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.2);
        border-radius: 5px;
        overflow: hidden;
    }

    .dropdown-content a {
        padding: 10px;
        display: block;
        color: #333;
        text-decoration: none;
        transition: 0.2s;
    }

    .dropdown-content a:hover {
        background: #f1f1f1;
    }

    .dropdown:hover .dropdown-content {
        display: block;
    }

    .cart a {
        background: #e67e22;
        padding: 8px 14px;
        border-radius: 5px;
        color: white;
        text-decoration: none;
        transition: 0.3s;
    }

    .cart a:hover {
        background: #d35400;
    }

    .login-btn, .signup-btn {
        background: #27ae60;
        padding: 8px 14px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        color: white;
        transition: 0.3s;
    }

    .login-btn:hover, .signup-btn:hover {
        background: #1e8449;
    }

    /* ===== CART CONTAINER ===== */
    .container {
        width: 90%;
        margin: 40px auto;
        background: #fff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }

    h2 {
        text-align: center;
        margin-bottom: 25px;
        color: #2c3e50;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        padding: 12px;
        text-align: center;
        border-bottom: 1px solid #eee;
    }

    th {
        background: #ecf0f1;
        font-weight: bold;
        color: #2c3e50;
    }

    img.cart-img {
        width: 90px;
        height: 70px;
        object-fit: cover;
        border-radius: 6px;
    }

    .btn {
        padding: 6px 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
    }

    .btn-inc { background: #2ecc71; color: white; }
    .btn-dec { background: #f39c12; color: white; }
    .btn-del { background: #e74c3c; color: white; }
    .btn-inc:hover { background: #27ae60; }
    .btn-dec:hover { background: #d68910; }
    .btn-del:hover { background: #c0392b; }

    .quantity-controls form {
        display: inline-block;
        margin: 0 5px;
    }

    .quantity-controls strong {
        font-size: 16px;
        vertical-align: middle;
    }

    .total-section {
        text-align: right;
        margin-top: 20px;
        font-size: 18px;
        color: #2c3e50;
    }

    .action-buttons {
        text-align: right;
        margin-top: 15px;
    }

    .action-buttons form {
        display: inline-block;
        margin-left: 10px;
    }

    .empty-msg {
        text-align: center;
        color: #7f8c8d;
        font-size: 18px;
        padding: 40px 0;
    }
</style>
</head>

<body>

<!-- ✅ SINGLE HEADER -->
<header>
    <h1 onclick="window.location.href='home'">UrbanEats</h1>
    <div class="nav-buttons">
        <c:choose>
            <c:when test="${not empty sessionScope.currentUser}">
                <div class="dropdown">
                    <button class="dropbtn">${sessionScope.currentUser.name} ▼</button>
                    <div class="dropdown-content">
                        <a href="logout">Logout</a>
                    </div>
                </div>
                <div class="cart">
                    <a href="cart.jsp">
                        🛒 Cart
                        <c:if test="${not empty sessionScope.cartCount}">
                            (${sessionScope.cartCount})
                        </c:if>
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <button class="login-btn" onclick="window.location.href='login.html'">Login</button>
                <button class="signup-btn" onclick="window.location.href='register.html'">Sign Up</button>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<!-- ✅ CART CONTENT -->
<div class="container">
    <h2>Your Cart</h2>

    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
    %>
        <p class="empty-msg">Your cart is empty.</p>
    <%
        } else {
            double total = 0.0;
            Collection<CartItem> items = cart.getItems();
    %>

    <table>
        <thead>
            <tr>
                <th>Image</th>
                <th>Item</th>
                <th>Price (₹)</th>
                <th>Quantity</th>
                <th>Subtotal (₹)</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (CartItem item : items) {
                double subtotal = item.getPrice() * item.getQuantity();
                total += subtotal;
        %>
            <tr>
                <td><img class="cart-img" src="<%= (item.getImagePath() != null && !item.getImagePath().isEmpty()) ? item.getImagePath() : "images/placeholder.png" %>" alt="image"></td>
                <td><%= item.getName() %></td>
                <td><%= String.format("%.2f", item.getPrice()) %></td>
                <td class="quantity-controls">
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="decrease">
                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                        <button type="submit" class="btn btn-dec" <%= (item.getQuantity() == 1) ? "disabled" : "" %>>−</button>
                    </form>
                    <strong><%= item.getQuantity() %></strong>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="increase">
                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                        <button type="submit" class="btn btn-inc">+</button>
                    </form>
                </td>
                <td><%= String.format("%.2f", subtotal) %></td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                        <button type="submit" class="btn btn-del">Remove</button>
                    </form>
                </td>
            </tr>
        <%
            } // end for
        %>
        </tbody>
    </table>

    <div class="total-section">
        <h3>Total: ₹<%= String.format("%.2f", total) %></h3>
    </div>

    <div class="action-buttons">
        <form action="checkout" method="get">
            <button type="submit" class="btn btn-inc" style="padding:10px 20px;">Proceed to Checkout</button>
        </form>
        <form action="MenuServlet" method="get">
            <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
            <button type="submit" class="btn" style="background:#3498db; color:white; padding:10px 20px;">Add More Items</button>
        </form>
    </div>

    <%
        } // end else
    %>
</div>

</body>
</html>
