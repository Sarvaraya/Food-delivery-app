<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<style>

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

    body {
        font-family: Arial, sans-serif;
        background: #f4f4f9;
        color: #333;
        padding: 20px;
    }
    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #2c3e50;
    }
    .order-card {
        background: white;
        padding: 20px;
        margin-bottom: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    }
    .order-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
        font-weight: bold;
        font-size: 16px;
    }
    .status {
        padding: 4px 10px;
        border-radius: 5px;
        color: white;
        font-weight: bold;
        font-size: 14px;
    }
    .Pending { background: #f39c12; }
    .Processing { background: #3498db; }
    .Completed { background: #27ae60; }
    .Cancelled { background: #e74c3c; }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f1f1f1;
    }
    .no-orders {
        text-align: center;
        font-size: 18px;
        margin-top: 50px;
        color: #7f8c8d;
    }
</style>
</head>
<body>


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
<h2>My Orders</h2>
<c:choose>
    <c:when test="${not empty orders}">
        <c:forEach var="order" items="${orders}">
            <div class="order-card">
                <div class="order-header">
                    <span>Order ID: ${order.orderid}</span>
                    <span class="status ${order.status}">${order.status}</span>
                </div>
                <div>Order Date: ${order.orderDate}</div>
                <div>Payment Mode: ${order.paymentMode}</div>
                <div>Total Amount: ₹${order.totalAmount}</div>

                <c:set var="items" value="${orderItemsMap[order.orderid]}" />
                <c:if test="${not empty items}">
                    <table>
                        <thead>
                            <tr>
                                <th>Menu ID</th>
                                <th>Quantity</th>
                                <th>Price (₹)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}">
                                <tr>
                                    <td>${item.menuid}</td>
                                    <td>${item.quantity}</td>
                                    <td>${item.totalPrice}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p class="no-orders">You have not placed any orders yet.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
