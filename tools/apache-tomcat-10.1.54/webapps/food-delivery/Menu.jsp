<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.fda.model.Menu" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Menu | UrbanEats</title>
<link rel="stylesheet" href="menu.css">
<style>
/* ===== HEADER STYLING ===== */
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

.nav-section {
    display: flex;
    align-items: center;
    gap: 20px;
}

.search-bar input {
    padding: 8px 12px;
    border-radius: 5px;
    border: none;
    width: 220px;
    outline: none;
}

.search-bar button {
    padding: 8px 12px;
    background: #27ae60;
    border: none;
    color: white;
    border-radius: 5px;
    cursor: pointer;
}

.search-bar button:hover {
    background: #1e8449;
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

/* ===== MENU ITEMS ===== */
.menu-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    padding: 20px;
    gap: 25px;
    background: #f9f9f9;
}

.menu-card {
    background: white;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    width: 260px;
    text-align: center;
    overflow: hidden;
    transition: transform 0.2s ease;
}

.menu-card:hover {
    transform: translateY(-5px);
}

.menu-card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
}

.menu-info {
    padding: 15px;
}

.menu-info h3 {
    margin: 5px 0;
    font-size: 20px;
    color: #2c3e50;
}

.description {
    color: #7f8c8d;
    font-size: 14px;
    margin: 8px 0;
}

.price, .rating {
    font-weight: bold;
    margin: 5px 0;
}

.cart-form button {
    background: #27ae60;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    margin-bottom: 15px;
    transition: 0.3s;
}

.cart-form button:hover {
    background: #1e8449;
}

.empty-msg {
    text-align: center;
    margin-top: 50px;
    font-size: 18px;
    color: #555;
}
</style>
</head>

<body>

<!-- ✅ HEADER -->
<header>
    <h1 onclick="window.location.href='home'">UrbanEats</h1>

    <div class="nav-section">
        <!-- 🔍 SEARCH BAR -->
        <form action="SearchServlet" method="get" class="search-bar">
            <input type="text" name="keyword" placeholder="Search restaurants or dishes..." required>
            <button type="submit">Search</button>
        </form>

        <!-- 👤 USER + CART -->
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

<!-- ✅ MENU ITEMS -->
<div class="menu-container">
    <%
        List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");
        if (menuItems != null && !menuItems.isEmpty()) {
            for (Menu item : menuItems) {
    %>
    <div class="menu-card">
        <img src="<%= item.getImagePath() %>" alt="<%= item.getItemName() %>">
        <div class="menu-info">
            <h3><%= item.getItemName() %></h3>
            <p class="description"><%= item.getDescription() %></p>
            <p class="price">₹ <%= String.format("%.2f", item.getPrice()) %></p>
            <p class="rating">⭐ <%= String.format("%.1f", item.getRating()) %></p>
        </div>

        <form action="cart" method="post" class="cart-form">
            <input type="hidden" name="itemId" value="<%= item.getMenuid() %>">
            <input type="hidden" name="quantity" value="1">
            <input type="hidden" name="restaurantId" value="<%= item.getRestaurantid() %>">
            <input type="hidden" name="action" value="add">
            <button type="submit">Add to Cart</button>
        </form>
    </div>
    <%
            }
        } else {
    %>
    <p class="empty-msg">No menu items found.</p>
    <%
        }
    %>
</div>

</body>
</html>
