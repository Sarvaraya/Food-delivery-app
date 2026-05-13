<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout - Food Delivery</title>

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #ff9966, #ff5e62);
            margin: 0;
            padding: 0;
        }

        .checkout-container {
            width: 450px;
            margin: 80px auto;
            background: white;
            padding: 40px 50px;
            border-radius: 20px;
            box-shadow: 0px 8px 20px rgba(0,0,0,0.15);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        label {
            font-weight: 600;
            color: #444;
            display: block;
            margin-bottom: 8px;
        }

        textarea, select, input[type="text"] {
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            border: 1px solid #ccc;
            margin-bottom: 20px;
            font-size: 14px;
            box-sizing: border-box;
        }

        textarea:focus, select:focus, input:focus {
            border-color: #ff5e62;
            outline: none;
        }

        button {
            width: 100%;
            background: linear-gradient(90deg, #ff9966, #ff5e62);
            color: white;
            font-size: 16px;
            padding: 12px;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        button:hover {
            background: linear-gradient(90deg, #ff5e62, #ff9966);
            transform: scale(1.02);
        }

        .summary {
            background: #f9f9f9;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 25px;
            color: #333;
        }

        .summary h3 {
            margin-top: 0;
            color: #ff5e62;
        }

        .back-link {
            display: inline-block;
            margin-top: 15px;
            color: #ff5e62;
            text-decoration: none;
            font-size: 14px;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="checkout-container">
        <h2>Checkout</h2>

        <!-- Order Summary (optional, static or you can dynamically load from session) -->
        <div class="summary">
            <h3>Your Order Summary</h3>
            <p>Items in your cart will be delivered soon!</p>
        </div>

        <% if (request.getAttribute("error") != null) { %>
            <p style="color:#c0392b; text-align:center;"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="checkout" method="post">
            <label for="address">Delivery Address</label>
            <textarea name="address" id="address" rows="3" required placeholder="Enter your complete address..."></textarea>

            <label for="paymentMode">Payment Mode</label>
            <select name="paymentMode" id="paymentMode" required>
                <option value="">-- Select Payment Method --</option>
                <option value="COD">Cash on Delivery</option>
                <option value="CARD">Credit/Debit Card</option>
                <option value="UPI">UPI</option>
            </select>

            <button type="submit">Place Order</button>
        </form>

        <a href="cart.jsp" class="back-link">← Back to Cart</a>
    </div>

</body>
</html>
