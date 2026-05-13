<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>UrbanEats | Home</title>
<style>
/* --- GENERAL --- */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Arial', sans-serif;
}

body {
    background: #f4f4f9;
    color: #333;
}

a {
    text-decoration: none;
    color: inherit;
}

/* --- MODERN HEADER --- */
header {
    position: sticky;
    top: 0;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 40px;
    background: #fff;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
    z-index: 1000;
    transition: 0.3s;
    backdrop-filter: blur(10px);
}

header h1 {
    cursor: pointer;
    font-size: 28px;
    font-weight: 700;
    color: #ff6b00;
    transition: 0.3s;
}

header h1:hover {
    transform: scale(1.05);
}

.nav-buttons {
    display: flex;
    align-items: center;
    gap: 15px;
}

/* Buttons & cart */
.nav-buttons a, .login-btn, .signup-btn {
    padding: 8px 18px;
    border-radius: 25px;
    font-size: 14px;
    font-weight: 500;
    border: none;
    cursor: pointer;
    transition: 0.3s;
}

.login-btn, .signup-btn {
    background: linear-gradient(135deg, #27ae60, #2ecc71);
    color: white;
}

.login-btn:hover, .signup-btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(39, 174, 96, 0.4);
}

.cart a {
    background: linear-gradient(135deg, #e67e22, #d35400);
    color: white;
}

.cart a:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(230, 126, 34, 0.4);
}

/* Dropdown */
.dropdown {
    position: relative;
}

.dropbtn {
    background: linear-gradient(135deg, #34495e, #2c3e50);
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 20px;
    cursor: pointer;
    transition: 0.3s;
}

.dropbtn:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(52, 73, 94, 0.4);
}

.dropdown-content {
    display: none;
    position: absolute;
    top: 120%;
    left: 0;
    min-width: 150px;
    background: #fff;
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
    border-radius: 12px;
    overflow: hidden;
    z-index: 999;
}

.dropdown-content a {
    display: block;
    padding: 12px;
    color: #333;
    transition: 0.3s;
}

.dropdown-content a:hover {
    background: #f3f3f3;
}

.dropdown:hover .dropdown-content {
    display: block;
}

/* --- HERO SECTION --- */
.hero {
    background: linear-gradient(to right, #e67e22, #f39c12);
    color: white;
    text-align: center;
    padding: 80px 20px;
    position: relative;
}

.hero h2 {
    font-size: 42px;
    margin-bottom: 15px;
}

.hero p {
    font-size: 18px;
    margin-bottom: 25px;
}

.search-bar {
    display: flex;
    justify-content: center;
    gap: 10px;
    max-width: 600px;
    margin: auto;
}

.search-bar input {
    width: 70%;
    padding: 12px 15px;
    border: none;
    border-radius: 30px;
    outline: none;
    font-size: 16px;
}

.search-bar button {
    padding: 12px 25px;
    border: none;
    border-radius: 30px;
    background: #34495e;
    color: white;
    cursor: pointer;
    font-size: 16px;
    transition: 0.3s;
}

.search-bar button:hover {
    background: #2c3e50;
}

/* --- RESTAURANT SECTION --- */
.restaurant-section {
    padding: 50px 40px;
}

.restaurant-section h2 {
    text-align: center;
    font-size: 36px;
    margin-bottom: 40px;
}

.restaurant-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 25px;
}

.restaurant-card {
    background: white;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    transition: 0.3s;
}

.restaurant-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.restaurant-card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
}

.restaurant-info {
    padding: 20px;
}

.restaurant-info h3 {
    font-size: 22px;
    margin-bottom: 10px;
}

.restaurant-info .rating {
    font-size: 16px;
    color: #f1c40f;
    margin-bottom: 5px;
}

.restaurant-info .arrival-time {
    font-size: 14px;
    color: #7f8c8d;
    margin-bottom: 8px;
}

.restaurant-info .description {
    font-size: 14px;
    color: #95a5a6;
}

/* --- FOOTER --- */
footer {
    background: #2c3e50;
    color: white;
    padding: 40px;
    margin-top: 50px;
}

.footer-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    gap: 20px;
}

.footer-section {
    min-width: 180px;
}

.footer-section h3 {
    margin-bottom: 15px;
    font-size: 18px;
}

.footer-section a {
    display: block;
    margin-bottom: 8px;
    color: white;
    transition: 0.3s;
}

.footer-section a:hover {
    color: #f39c12;
    text-decoration: underline;
}

.footer-bottom {
    text-align: center;
    margin-top: 30px;
    color: #bdc3c7;
    font-size: 14px;
}

/* --- RESPONSIVE --- */
@media (max-width: 768px) {
    header {
        flex-direction: column;
        gap: 10px;
        padding: 15px 20px;
    }

    .nav-buttons {
        flex-wrap: wrap;
        justify-content: center;
    }

    .search-bar {
        flex-direction: column;
    }

    .search-bar input {
        width: 100%;
    }

    .restaurant-section {
        padding: 30px 20px;
    }
}
</style>
</head>
<body>

<!-- HEADER -->
<header>
    <h1 onclick="window.location.href='home.jsp'">UrbanEats</h1>
    <div class="nav-buttons">
        <c:choose>
            <c:when test="${not empty sessionScope.currentUser}">
                <div class="dropdown">
                    <button class="dropbtn">${sessionScope.currentUser.name} ▼</button>
                    <div class="dropdown-content">
                        <a href="myorders">Orders</a>
                        <a href="logout">Logout</a>
                    </div>
                </div>
                <div class="cart">
                    <a href="cart.jsp"> 🛒 Cart
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

<!-- HERO -->
<section class="hero">
    <h2>Discover the best food & drinks in your city</h2>
    <p>Explore top restaurants, cafes, and cuisines near you</p>
    <form action="SearchServlet" method="get" class="search-bar">
        <input type="text" name="keyword" placeholder="Search for restaurant or cuisine..." required />
        <button type="submit">Search</button>
    </form>
</section>

<!-- POPULAR RESTAURANTS -->
<section class="restaurant-section">
    <h2>Popular Restaurants</h2>
    <div class="restaurant-container">
        <c:forEach var="r" items="${restaurant}">
            <a href="MenuServlet?restaurantId=${r.restaurantid}">
                <div class="restaurant-card">
                    <img src="${r.imagePath}" alt="${r.name}" />
                    <div class="restaurant-info">
                        <h3>${r.name}</h3>
                        <div class="rating">⭐ ${r.rating}</div>
                        <div class="arrival-time">⏱ ${r.eta} mins</div>
                        <p class="description">${r.cusineType}</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<!-- FOOTER -->
<footer>
    <div class="footer-container">
        <div class="footer-section">
            <h3>About FoodFinder</h3>
            <a href="#">Who We Are</a>
            <a href="#">Blog</a>
            <a href="#">Careers</a>
        </div>
        <div class="footer-section">
            <h3>Contact</h3>
            <a href="#">Help & Support</a>
            <a href="#">Partner With Us</a>
            <a href="#">Advertise</a>
        </div>
        <div class="footer-section">
            <h3>Social Links</h3>
            <a href="https://www.instagram.com/mr_sarvaraya/">Instagram</a>
            <a href="#">Facebook</a>
            <a href="#">Twitter</a>
        </div>
    </div>
    <div class="footer-bottom">© 2025 FoodFinder — Made with ❤️ by Sarvaraya</div>
</footer>

</body>
</html>
