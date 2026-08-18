<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.model.Cart1, com.food.model.Cartitem1, java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout - FoodZone</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/checkout.css">
</head>
<body>

    <%
        Cart1 cart = (Cart1) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("resturantId");

        double grandTotal = 0;
        double deliveryFee = 40;
        double platformFee = 5;
        double finalAmount = 0;

        int totalCount = 0;
        if (cart != null && !cart.getItems().isEmpty()) {
            for (Cartitem1 item : cart.getItems().values()) {
                grandTotal += item.getTotalPrice();
                totalCount += item.getQuantity();
            }
            finalAmount = grandTotal + deliveryFee + platformFee;
        }
    %>

    <!-- Navbar -->
    <header class="navbar">
        <div class="nav-left">
            <a href="menu?restaurantID=<%= restaurantId %>" class="logo">🔥 FoodZone</a>
            <div class="location-badge">📍 Bangalore</div>
        </div>
        <div class="nav-right">
            <a href="menu?restaurantID=<%= restaurantId %>" class="nav-link">🏠 Home</a>
            <a href="callResturantServlet" class="nav-link">🍽️ Restaurants</a>
            <a href="profile.jsp" class="nav-link">👤 Profile</a>
            <a href="	Cart.jsp" class="nav-link">
                🛒 Cart
                <span class="cart-btn-badge"><%= totalCount %></span>
            </a>
        </div>
    </header>

    <main class="main-wrapper">
        <div class="checkout-heading">
            <h1 class="page-title">Checkout</h1>
            <p class="page-subtitle">Confirm your delivery details and place your order</p>
        </div>

        <%
            if (cart != null && !cart.getItems().isEmpty()) {
        %>

        <form action="checkoutServlet" method="post">
            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
            <input type="hidden" name="amount" value="<%= finalAmount %>">

            <div class="checkout-wrapper">
                <!-- Delivery & Payment Form -->
                <div class="checkout-left">
                    <div class="form-card">
                        <div class="section-header">📍 Delivery Address</div>
                            <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" required class="form-control">
                        </div>

                        <div class="form-group">
                            <label>Delivery Address</label>
                            <input type="text" name="address" required class="form-control" placeholder="Enter complete address, landmark, flat no.">
                        </div>
          
                        <div class="form-row">
                            <div class="form-group">
                                <label>City</label>
                                <input type="text" name="city"  required class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Pincode</label>
                                <input type="text" name="pincode" required class="form-control" placeholder="560103">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input type="tel" name="phone" required class="form-control" placeholder="10-digit mobile number">
                        </div>
                    </div>

                    <div class="form-card">
                        <div class="section-header">💳 Payment Mode</div>
                        <div class="payment-options">
                            <label class="payment-label">
                                <input type="radio" name="paymentMode" value="COD" checked>
                                <div class="payment-text">
                                    <strong>Cash on Delivery (COD)</strong>
                                    <span>Pay with cash or UPI on delivery</span>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>

                <!-- Order Summary Side -->
                <div class="checkout-right">
                    <div class="summary-card">
                        <h2 class="summary-title">Order Summary</h2>

                        <!-- Individual Cart Items List -->
                        <div class="summary-items-list">
                            <%
                                for (Cartitem1 item : cart.getItems().values()) {
                            %>
                            <div class="summary-item-row">
                                <span class="summary-item-name"><%= item.getName() %></span>
                                <span class="summary-item-qty">x <%= item.getQuantity() %></span>
                                <span class="summary-item-price">₹<%= String.format("%.0f", item.getTotalPrice()) %></span>
                            </div>
                            <%
                                }
                            %>
                        </div>

                        <div class="summary-divider"></div>

                        <!-- Bill Details -->
                        <div class="summary-row">
                            <span>Item Total</span>
                            <span>₹<%= String.format("%.1f", grandTotal) %></span>
                        </div>
                        <div class="summary-row">
                            <span>Delivery Fee</span>
                            <span>₹<%= String.format("%.1f", deliveryFee) %></span>
                        </div>
                        <div class="summary-row">
                            <span>Platform Fee</span>
                            <span>₹<%= String.format("%.1f", platformFee) %></span>
                        </div>

                        <div class="summary-divider"></div>

                        <!-- Total Payable -->
                        <div class="summary-total-row">
                            <span class="total-label">Total</span>
                            <span class="total-value">₹<%= String.format("%.1f", finalAmount) %></span>
                        </div>

                        <button type="submit" class="btn-place-order">Proceed to Pay →</button>
                    </div>
                </div>
            </div>
        </form>

        <%
            } else {
        %>
            <div class="empty-cart-view">
                <div style="font-size: 3rem; margin-bottom: 12px;">🛒</div>
                <h2>Your Cart is Empty</h2>
                <p style="color: #9ca3af; margin: 10px 0 20px;">Add items to your cart before proceeding to checkout.</p>
                <a href="callResturantServlet" class="btn-place-order" style="display:inline-block; width: auto; padding: 12px 28px; text-decoration: none;">Explore Restaurants</a>
            </div>
        <%
            }
        %>
    </main>

    <!-- Footer Section -->
    <footer class="footer-container">
        <div class="footer-wrapper">
            <!-- Col 1: Brand -->
            <div class="footer-col brand-col">
                <div class="footer-brand">
                    <span class="brand-icon">🍔</span>
                    <span class="brand-name">FoodZone</span>
                </div>
                <p class="brand-desc">
                    Delicious food from the best restaurants, delivered fresh and fast to your doorstep.
                </p>
                <div class="tagline-badge">
                    🍴 Eat Better. Order Faster.
                </div>
            </div>

            <!-- Col 2: FoodZone Links -->
            <div class="footer-col">
                <h4 class="col-title">FoodZone</h4>
                <ul class="footer-links">
                    <li><a href="about.html">About Us</a></li>
                    <li><a href="story.html">Our Story</a></li>
                    <li><a href="careers.html">Careers</a></li>
                    <li><a href="partner.html">Partner With Us</a></li>
                </ul>
            </div>

            <!-- Col 3: Explore Links -->
            <div class="footer-col">
                <h4 class="col-title">Explore</h4>
                <ul class="footer-links">
                    <li><a href="callResturantServlet">Restaurants</a></li>
                    <li><a href="offers.jsp">Offers</a></li>
                    <li><a href="menu.jsp">Popular Food</a></li>
                    <li><a href="orders.jsp">My Orders</a></li>
                </ul>
            </div>

            <!-- Col 4: What We Offer -->
            <div class="footer-col">
                <h4 class="col-title">What We Offer</h4>
                <ul class="footer-links static-list">
                    <li><span>🍕 Wide Food Selection</span></li>
                    <li><span>🚀 Fast Delivery</span></li>
                    <li><span>💰 Best Deals</span></li>
                    <li><span>⭐ Top Restaurants</span></li>
                </ul>
            </div>

            <!-- Col 5: Contact Us Pills -->
            <div class="footer-col contact-col">
                <h4 class="col-title">Contact Us</h4>
                <div class="contact-pills-list">
                    <div class="contact-pill">
                        <span class="pill-icon">📍</span>
                        <span class="pill-text">Bangalore, India</span>
                    </div>
                    <a href="tel:+918309112610" class="contact-pill">
                        <span class="pill-icon">📞</span>
                        <span class="pill-text">+91 83091 12610</span>
                    </a>
                    <a href="mailto:ashokdeva077@gmail.com" class="contact-pill">
                        <span class="pill-icon">✉️</span>
                        <span class="pill-text">ashokdeva077@gmail.com</span>
                    </a>
                    <div class="contact-pill">
                        <span class="pill-icon">⏰</span>
                        <span class="pill-text">24 × 7 Support</span>
                    </div>
                </div>
            </div>
        </div>
    </footer>

</body>
</html>