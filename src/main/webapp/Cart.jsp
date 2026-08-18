<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.food.model.Cart1, com.food.model.Cartitem1, java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart - FoodZone</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Cart.css">
</head>
<body>

    <%
        Cart1 cart = (Cart1) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("resturantId");
        
        // Correct menu route using your servlet mapping
        String menuUrl = (restaurantId != null) 
            ? "menu?restaurantID=" + restaurantId 
            : "callResturantServlet";

        int totalCount = 0;
        if (cart != null && cart.getItems() != null) {
            for (Cartitem1 ci : cart.getItems().values()) {
                totalCount += ci.getQuantity();
            }
        }
    %>

    <!-- Header Navigation -->
    <header class="navbar">
        <div class="nav-left">
            <a href="<%= menuUrl %>" class="logo">🔥 FoodZone</a>
            <div class="location-badge">📍 Bangalore</div>
        </div>
        <div class="nav-center">
            <input type="text" class="search-bar" placeholder="Search for dishes, restaurants...">
        </div>
        <div class="nav-right">
            <a href="<%= menuUrl %>" class="nav-link">🏠 Home</a>
            <a href="callResturantServlet" class="nav-link">🍽️ Restaurants</a>
            <a href="profile.jsp" class="nav-link">👤 Profile</a>
            <a href="Cart.jsp" class="nav-link active">
                🛒 Cart 
                <span class="cart-btn-badge"><%= totalCount %></span>
            </a>
        </div>
    </header>

    <main class="main-wrapper">
        <!-- Return to Same Restaurant Menu -->
        <a class="back-link" href="menu?restaurantID=<%= restaurantId %>">← Continue Shopping</a>
        <h1 class="page-title">Your Cart (<%= totalCount %>)</h1>
        <p class="page-subtitle">Almost there! Review your items and place your order.</p>

        <%
            Map<Integer, Cartitem1> items = (cart != null) ? cart.getItems() : null;

            if (items == null || items.isEmpty()) {
        %>
            <div class="empty-cart-view">
                <div style="font-size: 3rem; margin-bottom: 12px;">🛒</div>
                <h2>Your Cart is Empty</h2>
                <p style="color: #9ca3af; margin: 10px 0 20px;">Explore our menu and add some delicious food!</p>
                <a href="<%= menuUrl %>" class="btn-checkout" style="display:inline-flex; width: auto; padding: 12px 28px;">Explore Menu</a>
            </div>
        <%
            } else {
                double subTotal = cart.getTotalprice();
                double deliveryCharges = (subTotal > 199 || subTotal == 0) ? 0.00 : 20.00;
                double platformFee = 10.00;
                double packagingCharges = 10.00;
                double grandTotal = subTotal + deliveryCharges + platformFee + packagingCharges;
        %>

        <div class="cart-layout">
            <!-- Left Side: Cart Items -->
            <div class="cart-items-section">
                <div class="section-header">🛍️ Cart Items</div>

                <%
                    for (Cartitem1 item : items.values()) {
                %>
                <div class="cart-item-card">
                    <!-- Dynamic Menu Image -->
                    <img src="<%= (item.getImagePath() != null && !item.getImagePath().trim().isEmpty()) ? item.getImagePath() : "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=300&q=80" %>" 
                         alt="<%= item.getName() %>" 
                         class="item-img"
                         onerror="this.src='https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=300&q=80';">
                    
                    <div class="item-info">
                        <div class="item-title-row">
                            <div class="item-name"><%= item.getName() %> 🔴</div>
                            <div class="item-price">₹<%= String.format("%.0f", item.getPrice()) %></div>
                        </div>

                        <div class="restaurant-tag">Restaurant Partner</div>
                        <div class="item-desc">Freshly prepared with authentic ingredients and aromatic spices.</div>

                        <div class="item-actions-row">
                            <div class="item-meta">
                                <span class="rating-badge">★ 4.5</span>
                                <button type="button" class="note-btn">📝 Add Note</button>
                            </div>

                            <div class="qty-remove-wrap">
                                <div class="qty-pill">
                                    <!-- Decrease Quantity Button -->
                                    <form action="callCartServlet" method="post" style="margin:0; padding:0; display:inline;">
                                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                                        <input type="hidden" name="restaurantID" value="<%= restaurantId %>">
                                        <% if (item.getQuantity() <= 1) { %>
                                            <input type="hidden" name="action" value="delete">
                                        <% } else { %>
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                                        <% } %>
                                        <button type="submit" class="btn-qty">-</button>
                                    </form>

                                    <span class="qty-val"><%= item.getQuantity() %></span>

                                    <!-- Increase Quantity Button -->
                                    <form action="callCartServlet" method="post" style="margin:0; padding:0; display:inline;">
                                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                                        <input type="hidden" name="restaurantID" value="<%= restaurantId %>">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                                        <button type="submit" class="btn-qty">+</button>
                                    </form>
                                </div>

                                <!-- Remove Button -->
                                <form action="callCartServlet" method="post" style="margin:0; padding:0; display:inline;">
                                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                                    <input type="hidden" name="restaurantID" value="<%= restaurantId %>">
                                    <input type="hidden" name="action" value="delete">
                                    <button type="submit" class="btn-remove">🗑️ Remove</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>

                <!-- Free Delivery Progress -->
                <div class="delivery-offer-card">
                    <div class="offer-text">
                        🏷️ Add items worth <strong>₹<%= String.format("%.0f", Math.max(0, 199 - subTotal)) %></strong> more to get <strong>FREE delivery!</strong>
                    </div>
                    <div class="progress-bar-bg">
                        <div class="progress-bar-fill" style="width: <%= Math.min(100, (subTotal / 199.0) * 100) %>%;"></div>
                    </div>
                </div>
            </div>

            <!-- Right Side: Order Summary -->
            <div class="summary-section">
                <div class="summary-card">
                    <div class="section-header">🧾 Order Summary</div>

                    <div class="summary-row">
                        <span>Item Total (<%= totalCount %> items)</span>
                        <span>₹<%= String.format("%.0f", subTotal) %></span>
                    </div>
                    <div class="summary-row">
                        <span>Delivery Charges</span>
                        <span><%= deliveryCharges == 0 ? "FREE" : "₹" + String.format("%.0f", deliveryCharges) %></span>
                    </div>
                    <div class="summary-row">
                        <span>Platform Fee</span>
                        <span>₹<%= String.format("%.0f", platformFee) %></span>
                    </div>
                    <div class="summary-row">
                        <span>Packaging Charges</span>
                        <span>₹<%= String.format("%.0f", packagingCharges) %></span>
                    </div>



                    <div class="total-row">
                        <span>To Pay</span>
                        <span>₹<%= String.format("%.0f", grandTotal) %></span>
                    </div>
                    <div class="savings-label">✨ You're saving on delivery fees with FoodZone!</div>

                    <a href="checkout.jsp" class="btn-checkout">Proceed to Checkout →</a>

                    <div class="security-badge">
                        <span>🛡️</span>
                        <div>
                            <strong style="color: #fff; display:block;">Safe & Secure Payments</strong>
                            100% secure payments on FoodZone
                        </div>
                    </div>
                </div>

                <!-- Why Order Feature List -->
                <div class="features-card">
                    <div style="font-weight:600; margin-bottom:10px;">Why order from FoodZone?</div>
                    <div class="feature-item">
                        <span>🛵</span>
                        <div>
                            <div class="feature-title">Free Delivery</div>
                            <div class="feature-sub">On orders above ₹199</div>
                        </div>
                    </div>
                    <div class="feature-item">
                        <span>⏱️</span>
                        <div>
                            <div class="feature-title">Fast Delivery</div>
                            <div class="feature-sub">Delivery in 25-30 mins</div>
                        </div>
                    </div>
                    <div class="feature-item">
                        <span>⭐</span>
                        <div>
                            <div class="feature-title">Best Offers</div>
                            <div class="feature-sub">Exciting offers & discounts</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </main>

   <!-- Footer Section -->
<footer class="footer-container">
    <div class="footer-wrapper">
        <!-- Col 1: Brand & Tagline -->
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
                <li><a href="our-story.html">Our Story</a></li>
                <li><a href="careers.html">Careers</a></li>
                <li><a href="partner.html">Partner With Us</a></li>
            </ul>
        </div>

   
<!-- Col 3: Explore Links -->
<div class="footer-col">
    <h4 class="col-title">Explore</h4>
    <ul class="footer-links">
        <li><a href="CallResturantServlet">Restaurants</a></li>
        <li><a href="#">Offers</a></li>
        <li><a href="#">Popular Food</a></li>
        <li><a href="#">My Orders</a></li>
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