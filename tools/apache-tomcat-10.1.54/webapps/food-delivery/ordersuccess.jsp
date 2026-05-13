<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Success</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #6ee7b7, #3b82f6);
            color: #333;
            text-align: center;
            padding-top: 100px;
        }
        .container {
            background: white;
            display: inline-block;
            padding: 40px 60px;
            border-radius: 20px;
            box-shadow: 0px 8px 20px rgba(0,0,0,0.15);
        }
        h1 {
            color: #10b981;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #3b82f6;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: 0.3s;
        }
        a:hover {
            background: #2563eb;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎉 Order Placed Successfully!</h1>
        <p>
            <strong>
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "Thank you for your order!" %>
            </strong>
        </p>
        <a href="home">Go Back to Home</a>
    </div>
</body>
</html>
