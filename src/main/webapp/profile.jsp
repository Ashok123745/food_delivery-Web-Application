<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    // Fetch logged in user from session
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null) {
        user = (User) session.getAttribute("user");
    }
    if (user == null) {
        response.sendRedirect("login.html");
        return;
    }

    // Format last login timestamp nicely
    String formattedLastLogin = "Active Now";
    if (user.getLastLoginDate() != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm:ss a");
        formattedLastLogin = sdf.format(user.getLastLoginDate());
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - FoodZone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* Ambient Gradient Shift */
        @keyframes bgShift {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        /* Floating Light Orbs */
        @keyframes moveOrb1 {
            0%, 100% { transform: translate(0px, 0px) scale(1); }
            50% { transform: translate(110px, 70px) scale(1.2); }
        }

        @keyframes moveOrb2 {
            0%, 100% { transform: translate(0px, 0px) scale(1); }
            50% { transform: translate(-90px, -80px) scale(1.25); }
        }

        /* Floating Food Particles */
        @keyframes floatParticles {
            0% {
                transform: translateY(100vh) rotate(0deg);
                opacity: 0;
            }
            15% { opacity: 0.16; }
            85% { opacity: 0.16; }
            100% {
                transform: translateY(-10vh) rotate(360deg);
                opacity: 0;
            }
        }

        /* Card Scale Entrance */
        @keyframes cardEntrance {
            0% {
                opacity: 0;
                transform: scale(0.92) translateY(25px);
            }
            100% {
                opacity: 1;
                transform: scale(1) translateY(0);
            }
        }

        /* Avatar Pulse Glow */
        @keyframes avatarGlow {
            0%, 100% {
                box-shadow: 0 0 15px rgba(255, 87, 34, 0.4);
            }
            50% {
                box-shadow: 0 0 30px rgba(255, 152, 0, 0.75);
            }
        }

        body {
            position: relative;
            background: linear-gradient(-45deg, #09090c, #141419, #1a120e, #0d0d11);
            background-size: 400% 400%;
            animation: bgShift 16s ease infinite;
            color: #e0e0e0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 30px 15px;
            overflow-x: hidden;
        }

        /* Floating Ambient Glow Orbs */
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
            top: 12%;
            left: 10%;
            animation: moveOrb1 18s ease-in-out infinite;
        }

        .orb-2 {
            width: 420px;
            height: 420px;
            background: #ff9800;
            bottom: 12%;
            right: 10%;
            animation: moveOrb2 22s ease-in-out infinite;
        }

        /* Floating Background Icons */
        .particle-container {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            pointer-events: none;
            z-index: 0;
            overflow: hidden;
        }

        .particle {
            position: absolute;
            bottom: -50px;
            color: #ff9800;
            animation: floatParticles linear infinite;
        }

        .p1 { left: 12%; font-size: 28px; animation-duration: 15s; animation-delay: 0s; }
        .p2 { left: 28%; font-size: 20px; animation-duration: 19s; animation-delay: 3s; }
        .p3 { left: 52%; font-size: 32px; animation-duration: 23s; animation-delay: 1s; }
        .p4 { left: 74%; font-size: 22px; animation-duration: 17s; animation-delay: 5s; }
        .p5 { left: 88%; font-size: 26px; animation-duration: 21s; animation-delay: 2s; }

        /* Floating Top-Left Back Button */
        .btn-top-back {
            position: fixed;
            top: 25px;
            left: 25px;
            z-index: 10;
            background: rgba(24, 24, 30, 0.75);
            backdrop-filter: blur(14px);
            -webkit-backdrop-filter: blur(14px);
            border: 1px solid rgba(255, 255, 255, 0.12);
            color: #e0e0e0;
            padding: 10px 18px;
            border-radius: 12px;
            text-decoration: none;
            font-weight: 600;
            font-size: 0.9rem;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.4);
            transition: all 0.25s ease;
        }

        .btn-top-back:hover {
            background: #ff5722;
            border-color: #ff5722;
            color: #fff;
            transform: translateX(-3px);
            box-shadow: 0 8px 24px rgba(255, 87, 34, 0.4);
        }

        /* Glassmorphism Profile Card */
        .profile-card {
            position: relative;
            z-index: 2;
            background: rgba(24, 24, 30, 0.78);
            backdrop-filter: blur(18px);
            -webkit-backdrop-filter: blur(18px);
            border: 1px solid rgba(255, 255, 255, 0.09);
            border-radius: 20px;
            padding: 38px;
            max-width: 540px;
            width: 100%;
            box-shadow: 0 16px 40px rgba(0, 0, 0, 0.6);
            animation: cardEntrance 0.7s cubic-bezier(0.16, 1, 0.3, 1) forwards;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        .profile-card:hover {
            border-color: rgba(255, 87, 34, 0.4);
            box-shadow: 0 18px 45px rgba(255, 87, 34, 0.15), 0 10px 30px rgba(0, 0, 0, 0.7);
        }

        /* Avatar Circle with Neon Pulse */
        .avatar-circle {
            width: 82px;
            height: 82px;
            background: linear-gradient(135deg, #ff5722, #ff9800);
            color: #ffffff;
            font-size: 34px;
            font-weight: 800;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 16px auto;
            text-transform: uppercase;
            animation: avatarGlow 3s ease-in-out infinite;
        }

        .user-title {
            font-size: 1.6rem;
            font-weight: 800;
            color: #ffffff;
        }

        .role-badge {
            display: inline-block;
            background: rgba(255, 255, 255, 0.06);
            color: #ff9800;
            border: 1px solid rgba(255, 152, 0, 0.3);
            font-size: 0.75rem;
            letter-spacing: 1.2px;
            padding: 3px 12px;
            border-radius: 12px;
            font-weight: 700;
            text-transform: uppercase;
            margin-bottom: 22px;
        }

        /* Detail Rows */
        .detail-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 13px 0;
            border-bottom: 1px solid rgba(255, 255, 255, 0.06);
            font-size: 0.95rem;
        }

        .detail-label {
            color: #9e9e9e;
        }

        .detail-value {
            color: #f5f5f5;
            font-weight: 500;
            text-align: right;
            max-width: 60%;
            word-break: break-word;
        }

        /* Primary Action Button */
        .btn-custom-primary {
            background: linear-gradient(45deg, #ff5722, #ff7043);
            color: #fff;
            border: none;
            font-weight: 600;
            padding: 10px 16px;
            border-radius: 10px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 4px 16px rgba(255, 87, 34, 0.35);
            transition: all 0.25s ease;
            font-size: 0.88rem;
            flex: 1;
        }

        .btn-custom-primary:hover {
            background: linear-gradient(45deg, #e64a19, #f4511e);
            color: #fff;
            transform: translateY(-2px);
            box-shadow: 0 8px 22px rgba(255, 87, 34, 0.5);
        }

        /* Outline Action Button */
        .btn-custom-outline {
            background: rgba(255, 255, 255, 0.05);
            border: 1px solid rgba(255, 255, 255, 0.15);
            color: #ccc;
            font-weight: 600;
            padding: 10px 16px;
            border-radius: 10px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            transition: all 0.25s ease;
            font-size: 0.88rem;
            flex: 1;
        }

        .btn-custom-outline:hover {
            background: #ff5722;
            border-color: #ff5722;
            color: #fff;
            transform: translateY(-2px);
            box-shadow: 0 6px 18px rgba(255, 87, 34, 0.35);
        }

        /* Danger / Logout Button */
        .btn-custom-danger {
            background: rgba(239, 68, 68, 0.12);
            border: 1px solid rgba(239, 68, 68, 0.35);
            color: #fca5a5;
            font-weight: 600;
            padding: 10px 16px;
            border-radius: 10px;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            transition: all 0.25s ease;
            font-size: 0.88rem;
            flex: 1;
        }

        .btn-custom-danger:hover {
            background: #ef4444;
            border-color: #ef4444;
            color: #ffffff;
            transform: translateY(-2px);
            box-shadow: 0 6px 18px rgba(239, 68, 68, 0.4);
        }
    </style>
</head>
<body>

    <!-- Back Button to Return to Menu/Home -->
    <a href="menu" class="btn-top-back">
        <i class="fa-solid fa-arrow-left"></i>
        <span>Back to Home</span>
    </a>

    <!-- Animated Ambient Light Orbs -->
    <div class="bg-orb orb-1"></div>
    <div class="bg-orb orb-2"></div>

    <!-- Floating Background Food Particles -->
    <div class="particle-container">
        <i class="fa-solid fa-burger particle p1"></i>
        <i class="fa-solid fa-pizza-slice particle p2"></i>
        <i class="fa-solid fa-utensils particle p3"></i>
        <i class="fa-solid fa-bowl-rice particle p4"></i>
        <i class="fa-solid fa-mug-hot particle p5"></i>
    </div>

    <!-- Profile Glass Card -->
    <div class="profile-card text-center">
        <!-- Glowing Avatar Circle -->
        <div class="avatar-circle">
            <%= (user.getUsername() != null && !user.getUsername().isEmpty()) ? user.getUsername().substring(0, 1).toUpperCase() : "U" %>
        </div>

        <h3 class="user-title mb-1"><%= user.getUsername() %></h3>
        <div class="role-badge"><%= user.getRole() != null ? user.getRole() : "CUSTOMER" %></div>

        <!-- User Information -->
        <div class="text-start">
            <div class="detail-row">
                <span class="detail-label"><i class="fa-solid fa-user me-2 text-warning"></i>Username</span>
                <span class="detail-value"><%= user.getUsername() %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label"><i class="fa-solid fa-envelope me-2 text-warning"></i>Email</span>
                <span class="detail-value"><%= user.getEmail() != null ? user.getEmail() : "N/A" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label"><i class="fa-solid fa-phone me-2 text-warning"></i>Mobile</span>
                <span class="detail-value"><%= user.getMobile() != null ? user.getMobile() : "N/A" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label"><i class="fa-solid fa-location-dot me-2 text-warning"></i>Address</span>
                <span class="detail-value"><%= user.getAddress() != null ? user.getAddress() : "Not Provided" %></span>
            </div>
            <div class="detail-row">
                <span class="detail-label"><i class="fa-solid fa-clock me-2 text-warning"></i>Last Login</span>
                <span class="detail-value text-warning fw-semibold"><%= formattedLastLogin %></span>
            </div>
        </div>

        <!-- Action Buttons Grid -->
        <div class="d-flex justify-content-between gap-2 mt-4 pt-2">
            <a href="callResturantServlet" class="btn-custom-outline">
                <i class="fa-solid fa-utensils me-1"></i>Restaurants
            </a>
            <a href="orderHistoryServlet" class="btn-custom-primary">
                <i class="fa-solid fa-receipt me-1"></i>My Orders
            </a>
            <a href="logoutServlet" class="btn-custom-danger">
                <i class="fa-solid fa-right-from-bracket me-1"></i>Logout
            </a>
        </div>
    </div>

</body>
</html>