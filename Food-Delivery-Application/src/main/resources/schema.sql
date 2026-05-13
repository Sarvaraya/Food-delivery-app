CREATE TABLE IF NOT EXISTS user (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(30),
    address VARCHAR(255),
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastLoginDate TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS restaurant (
    restaurantid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    address VARCHAR(255),
    phone BIGINT,
    rating REAL,
    cusineType VARCHAR(120),
    isActive VARCHAR(10),
    eta TIME,
    adminUserid INT,
    imagePath VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS menu (
    menuId INT AUTO_INCREMENT PRIMARY KEY,
    restaurantId INT NOT NULL,
    itemName VARCHAR(120) NOT NULL,
    description VARCHAR(500),
    price DOUBLE NOT NULL,
    ratings REAL,
    isAvailable VARCHAR(10),
    imagePath VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS orders (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    restaurantId INT NOT NULL,
    orderDate TIMESTAMP NOT NULL,
    totalAmount DOUBLE NOT NULL,
    status VARCHAR(50),
    paymentMode VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS orderitem (
    orderItemId INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT NOT NULL,
    menuId INT NOT NULL,
    quantity INT NOT NULL,
    totalPrice DOUBLE NOT NULL
);

INSERT INTO user (userId, name, username, password, email, phone, address, createdDate)
SELECT 1, 'Demo User', 'demo', 'demo123', 'demo@example.com', '9999999999', 'Demo Street', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM user WHERE username = 'demo');

INSERT INTO restaurant (restaurantid, name, address, phone, rating, cusineType, isActive, eta, adminUserid, imagePath)
SELECT 1, 'Spice Street', 'MG Road', 9876543210, 4.6, 'Indian', 'yes', TIME '00:30:00', 1,
       'https://images.unsplash.com/photo-1601050690597-df0568f70950?auto=format&fit=crop&w=900&q=80'
WHERE NOT EXISTS (SELECT 1 FROM restaurant WHERE restaurantid = 1);

INSERT INTO restaurant (restaurantid, name, address, phone, rating, cusineType, isActive, eta, adminUserid, imagePath)
SELECT 2, 'Pasta Palace', 'Park Avenue', 9876500000, 4.4, 'Italian', 'yes', TIME '00:25:00', 1,
       'https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&w=900&q=80'
WHERE NOT EXISTS (SELECT 1 FROM restaurant WHERE restaurantid = 2);

INSERT INTO menu (menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath)
SELECT 1, 1, 'Paneer Butter Masala', 'Creamy paneer curry with warm spices.', 220.00, 4.7, 'yes',
       'https://images.unsplash.com/photo-1631452180519-c014fe946bc7?auto=format&fit=crop&w=900&q=80'
WHERE NOT EXISTS (SELECT 1 FROM menu WHERE menuId = 1);

INSERT INTO menu (menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath)
SELECT 2, 1, 'Veg Biryani', 'Fragrant rice with vegetables and masala.', 180.00, 4.5, 'yes',
       'https://images.unsplash.com/photo-1599043513900-ed6fe01d3833?auto=format&fit=crop&w=900&q=80'
WHERE NOT EXISTS (SELECT 1 FROM menu WHERE menuId = 2);

INSERT INTO menu (menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath)
SELECT 3, 2, 'Margherita Pizza', 'Classic tomato, basil, and mozzarella pizza.', 260.00, 4.6, 'yes',
       'https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?auto=format&fit=crop&w=900&q=80'
WHERE NOT EXISTS (SELECT 1 FROM menu WHERE menuId = 3);

INSERT INTO menu (menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath)
SELECT 4, 2, 'Alfredo Pasta', 'Creamy pasta with herbs and parmesan.', 240.00, 4.3, 'yes',
       'https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=900&q=80'
WHERE NOT EXISTS (SELECT 1 FROM menu WHERE menuId = 4);
