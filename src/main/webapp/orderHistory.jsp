<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.food.model.OrderHistoryDAO" %>
<%@ page import="com.food.model.OrderHistoryDAO.OrderItemDetail" %>
<%@ page import="com.food.model.User" %>
<%
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null) {
        user = (User) session.getAttribute("user");
    }
    if (user == null) {
        response.sendRedirect("login.html");
        return;
    }

    List<OrderHistoryDAO> orderList = (List<OrderHistoryDAO>) request.getAttribute("orderList");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders - FoodZone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        @keyframes bgShift {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        @keyframes moveOrb1 {
            0%, 100% { transform: translate(0px, 0px) scale(1); }
            50% { transform: translate(120px, 80px) scale(1.2); }
        }

        @keyframes moveOrb2 {
            0%, 100% { transform: translate(0px, 0px) scale(1); }
            50% { transform: translate(-100px, -90px) scale(1.25); }
        }

        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        body {
            position: relative;
            background: linear-gradient(-45deg, #09090c, #141419, #1c1512, #0d0d11);
            background-size: 400% 400%;
            animation: bgShift 16s ease infinite;
            color: #e0e0e0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
            padding: 50px 15px;
            overflow-x: hidden;
        }

        .bg-orb {
            position: fixed;
            border-radius: 50%;
            filter: blur(85px);
            pointer-events: none;
            z-index: 0;
            opacity: 0.35;
        }

        .orb-1 {
            width: 380px;
            height: 380px;
            background: #ff5722;
            top: 10%;
            left: 5%;
            animation: moveOrb1 18s ease-in-out infinite;
        }

        .orb-2 {
            width: 420px;
            height: 420px;
            background: #ff9800;
            bottom: 15%;
            right: 8%;
            animation: moveOrb2 22s ease-in-out infinite;
        }

        .history-container {
            position: relative;
            z-index: 2;
            max-width: 820px;
            margin: 0 auto;
        }

        .header-box {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 28px;
            background: rgba(22, 22, 28, 0.75);
            backdrop-filter: blur(18px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 18px;
            padding: 24px 30px;
            box-shadow: 0 12px 35px rgba(0, 0, 0, 0.55);
        }

        .page-title {
            background: linear-gradient(45deg, #ffffff, #ffa726);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            font-weight: 800;
        }

        .order-card {
            background: rgba(24, 24, 30, 0.8);
            backdrop-filter: blur(16px);
            border: 1px solid rgba(255, 255, 255, 0.08);
            border-radius: 16px;
            padding: 22px 26px;
            margin-bottom: 22px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.45);
            position: relative;
            overflow: hidden;
            opacity: 0;
            animation: fadeInUp 0.6s cubic-bezier(0.16, 1, 0.3, 1) forwards;
            transition: all 0.3s ease;
        }

        .order-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 4px;
            height: 100%;
            background: linear-gradient(180deg, #ff5722, #ff9800);
        }

        .order-card:hover {
            transform: translateY(-3px);
            border-color: rgba(255, 87, 34, 0.5);
            box-shadow: 0 14px 35px rgba(255, 87, 34, 0.2);
        }

        .badge-status {
            background: rgba(46, 125, 50, 0.3);
            color: #81c784;
            border: 1px solid rgba(129, 199, 132, 0.5);
            font-size: 0.75rem;
            padding: 4px 12px;
            border-radius: 20px;
            font-weight: 700;
        }

        .price-badge {
            font-size: 1.35rem;
            font-weight: 800;
            color: #ffb74d;
        }

        .items-box {
            background: rgba(0, 0, 0, 0.25);
            border-radius: 10px;
            padding: 12px 16px;
            margin: 14px 0;
            border: 1px solid rgba(255, 255, 255, 0.04);
        }

        .btn-custom-outline {
            background: rgba(255, 255, 255, 0.05);
            border: 1px solid rgba(255, 255, 255, 0.15);
            color: #e0e0e0;
            padding: 9px 20px;
            border-radius: 10px;
            text-decoration: none;
            font-weight: 600;
            font-size: 0.9rem;
            transition: all 0.25s ease;
        }

        .btn-custom-outline:hover {
            background: #ff5722;
            border-color: #ff5722;
            color: #fff;
        }
    </style>
</head>
<body>

    <div class="bg-orb orb-1"></div>
    <div class="bg-orb orb-2"></div>

    <div class="history-container">
        <!-- Header -->
        <div class="header-box">
            <div>
                <h3 class="page-title mb-1">Order History</h3>
                <p class="text-secondary small mb-0">
                    <i class="fa-solid fa-user-circle me-1 text-warning"></i> Orders placed by <span class="text-white fw-bold"><%= user.getUsername() %></span>
                </p>
            </div>
            <a href="profile.jsp" class="btn-custom-outline">
                <i class="fa-solid fa-arrow-left me-2"></i>Profile
            </a>
        </div>

        <!-- Orders -->
        <%
            if (orderList != null && !orderList.isEmpty()) {
                int index = 0;
                for (OrderHistoryDAO order : orderList) {
                    String formattedDate = order.getOrderDate() != null ? sdf.format(order.getOrderDate()) : "N/A";
                    double delay = 0.12 + (index * 0.08);
                    index++;
        %>
            <div class="order-card" style="animation-delay: <%= String.format(java.util.Locale.US, "%.2f", delay) %>s;">
                <!-- Header: Order ID, Status, Restaurant Name, and Total Price -->
                <div class="d-flex justify-content-between align-items-center border-bottom border-secondary border-opacity-25 pb-3 mb-2">
                    <div>
                        <div class="d-flex align-items-center gap-2 mb-1">
                            <span class="text-white fw-bold fs-5">Order #<%= order.getOrderId() %></span>
                            <span class="badge-status"><%= order.getStatus() != null ? order.getStatus() : "PLACED" %></span>
                        </div>
                        <div class="text-warning small fw-semibold">
                            <i class="fa-solid fa-store me-1"></i><%= order.getRestaurantName() %>
                        </div>
                    </div>
                    <span class="price-badge">₹<%= order.getTotalAmount() %></span>
                </div>

                <!-- Items List Box -->
                <div class="items-box">
                    <%
                        if (order.getItems() != null && !order.getItems().isEmpty()) {
                            for (OrderItemDetail item : order.getItems()) {
                    %>
                        <div class="d-flex justify-content-between text-light small py-1">
                            <span><i class="fa-solid fa-circle-dot me-2 text-warning" style="font-size: 8px;"></i><%= item.getItemName() %> <span class="text-secondary">(x<%= item.getQuantity() %>)</span></span>
                            <span class="text-secondary">₹<%= item.getPrice() %></span>
                        </div>
                    <%
                            }
                        } else {
                    %>
                        <span class="text-secondary small">Items details not available</span>
                    <%
                        }
                    %>
                </div>

                <!-- Footer: Date and Payment -->
                <div class="row text-secondary small align-items-center">
                    <div class="col-sm-6 mb-2 mb-sm-0">
                        <i class="fa-regular fa-clock me-2 text-warning"></i>Placed on: <span class="text-light"><%= formattedDate %></span>
                    </div>
                    <div class="col-sm-6 text-sm-end">
                        <i class="fa-solid fa-wallet me-2 text-info"></i>Payment: <span class="text-light"><%= order.getPaymentMode() %></span>
                    </div>
                </div>
            </div>
        <%
                }
            } else {
        %>
            <div class="order-card text-center py-5" style="animation-delay: 0.2s;">
                <i class="fa-solid fa-utensils text-secondary mb-3 opacity-50" style="font-size: 56px;"></i>
                <h4 class="text-white fw-bold">No Orders Placed Yet</h4>
                <p class="text-secondary small mb-4">Hungry? Explore our top restaurants and get delicious meals delivered to your doorstep.</p>
                <a href="callResturantServlet" class="btn btn-warning" style="background-color: #ff5722; border: none; color: #fff;">
                    <i class="fa-solid fa-compass me-2"></i>Explore Restaurants
                </a>
            </div>
        <%
            }
        %>
    </div>

</body>
</html>