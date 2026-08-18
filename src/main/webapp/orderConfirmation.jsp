<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.food.model.Cartitem1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Placed - FoodZone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #121212;
            color: #e0e0e0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .confirmation-card {
            background-color: #1e1e1e;
            border: 1px solid #2d2d2d;
            border-radius: 16px;
            padding: 35px 30px;
            max-width: 520px;
            width: 100%;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.6);
        }
        .success-icon {
            width: 70px;
            height: 70px;
            background: rgba(46, 125, 50, 0.2);
            color: #4caf50;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
            margin: 0 auto 16px auto;
        }
        .order-badge {
            background-color: #2a221b;
            color: #ff9800;
            border: 1px solid #ff9800;
            font-weight: 600;
            padding: 6px 18px;
            border-radius: 20px;
            display: inline-block;
            margin-bottom: 20px;
            font-size: 0.95rem;
        }
        .item-table {
            width: 100%;
            margin-top: 15px;
            border-collapse: collapse;
        }
        .item-table th {
            color: #888;
            font-size: 0.8rem;
            text-transform: uppercase;
            border-bottom: 1px solid #333;
            padding-bottom: 8px;
        }
        .item-table td {
            padding: 10px 0;
            border-bottom: 1px solid #2a2a2a;
            font-size: 0.95rem;
        }
        .total-row {
            font-size: 1.1rem;
            font-weight: 700;
            color: #ffffff;
            border-top: 2px solid #ff5722;
            padding-top: 14px;
        }
        .btn-custom {
            background-color: #ff5722;
            color: #ffffff;
            border: none;
            font-weight: 600;
            padding: 12px;
            border-radius: 8px;
            text-decoration: none;
            display: block;
            margin-top: 24px;
            transition: 0.3s;
        }
        .btn-custom:hover {
            background-color: #e64a19;
            color: #ffffff;
        }
    </style>
</head>
<body>

    <div class="confirmation-card text-center">
        <div class="success-icon">
            <i class="fa-solid fa-check"></i>
        </div>

        <h3 class="fw-bold text-white mb-1">Order Placed Successfully!</h3>
        <p class="text-secondary small mb-3">Thank you for ordering. Your food is on its way!</p>

        <div class="order-badge">
            Order ID: #<%= request.getAttribute("orderId") != null ? request.getAttribute("orderId") : "N/A" %>
        </div>

        <table class="item-table text-start">
            <thead>
                <tr>
                    <th>Item</th>
                    <th class="text-center">QTY</th>
                    <th class="text-end">PRICE</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Cartitem1> items = (List<Cartitem1>) request.getAttribute("orderedItems");
                    if (items != null && !items.isEmpty()) {
                        for (Cartitem1 item : items) {
                %>
                <tr>
                    <td class="text-white"><%= item.getName() != null ? item.getName() : "Item #" + item.getMenuId() %></td>
                    <td class="text-center text-secondary">x<%= item.getQuantity() %></td>
                    <td class="text-end text-white">₹<%= item.getPrice() * item.getQuantity() %></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

        <div class="d-flex justify-content-between total-row mt-3">
            <span>Total To Be Paid:</span>
            <span class="text-warning">₹<%= request.getAttribute("totalAmount") != null ? request.getAttribute("totalAmount") : "0.0" %></span>
        </div>

        <a href="callResturantServlet" class="btn-custom">
            <i class="fa-solid fa-house me-2"></i>Back to Restaurants
        </a>
    </div>

<!-- =====================================================
         CELEBRATION CONFETTI ANIMATION
    ====================================================== -->
    <script src="https://cdn.jsdelivr.net/npm/canvas-confetti@1.6.0/dist/confetti.browser.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // 1. Initial burst from center
            confetti({
                particleCount: 80,
                spread: 70,
                origin: { y: 0.6 },
                colors: ['#ff493d', '#10b981', '#ffb703', '#ffffff', '#6366f1']
            });

            // 2. Continuous celebratory side cannons for 2.5 seconds
            const duration = 2.5 * 1000;
            const animationEnd = Date.now() + duration;

            const interval = setInterval(function() {
                const timeLeft = animationEnd - Date.now();

                if (timeLeft <= 0) {
                    return clearInterval(interval);
                }

                // Left cannon
                confetti({
                    particleCount: 3,
                    angle: 60,
                    spread: 60,
                    origin: { x: 0, y: 0.7 },
                    colors: ['#ff493d', '#10b981', '#ffb703']
                });

                // Right cannon
                confetti({
                    particleCount: 3,
                    angle: 120,
                    spread: 60,
                    origin: { x: 1, y: 0.7 },
                    colors: ['#ff493d', '#10b981', '#ffb703']
                });
            }, 50);
        });
    </script>
</body>
</html>














</body>
</html>