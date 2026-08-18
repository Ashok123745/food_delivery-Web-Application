<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.LinkedHashMap, com.food.model.Menu, com.food.model.Resturant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodZone - Menu</title>
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <!-- External CSS -->
    <link rel="stylesheet" href="menu.css">
</head>
<body>

    <!-- Header Navigation -->
    <nav class="navbar">
        <div class="brand">
            <i class="fa-solid fa-utensils"></i> FoodZone
        </div>

        <div class="search-bar">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" id="menuSearchInput" placeholder="Search menu, dishes, or categories...">
        </div>

        <div class="nav-links">
            <a href="callResturantservlet" class="nav-item active"><i class="fa-solid fa-compass"></i> Explore</a>
            <a href="profile.jsp" class="nav-item"><i class="fa-regular fa-user"></i> Account</a>
            <a href="Cart.jsp" class="nav-item cart-icon">
                <i class="fa-solid fa-bag-shopping"></i>
                <span class="cart-badge">0</span>
            </a>
        </div>
    </nav>

    <div class="container">
        <a href="callResturantServlet" class="back-link"><i class="fa-solid fa-arrow-left"></i> Back to Restaurants</a>

        <!-- Dynamic Hero Header -->
        <%
            Resturant currentResturant = (Resturant) request.getAttribute("currentResturant");
            
            String restName = (currentResturant != null) ? currentResturant.getName() : "Restaurant";
            String cuisine = (currentResturant != null) ? currentResturant.getCuisineType() : "Multi Cuisine";
            double rating = (currentResturant != null) ? currentResturant.getRating() : 4.5;
            int deliveryTime = (currentResturant != null) ? currentResturant.getDeliveryTime() : 30;
            String imgPath = (currentResturant != null && currentResturant.getImagePath() != null) ? currentResturant.getImagePath() : "images/meghana.jpg";
        %>
        
        <section class="restaurant-hero" style="background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url('${pageContext.request.contextPath}/<%= imgPath %>') center/cover no-repeat;">
            <div class="restaurant-logo">
                <i class="fa-solid fa-bowl-rice fa-2x"></i>
                <span><%= restName.split(" ")[0] %></span>
            </div>
            <div class="restaurant-info">
                <div class="title-meta">
                    <h1><%= restName %></h1>
                    <span class="status-tag">Open Now</span>
                </div>
                <div class="restaurant-meta">
                    <span class="rating-badge"><i class="fa-solid fa-star"></i> <%= rating %></span>
                    <span class="reviews-count">(2,300+ Ratings)</span>
                    <span class="bullet">•</span>
                    <span><%= cuisine %></span>
                </div>
                <div class="restaurant-meta">
                    <span><i class="fa-regular fa-clock"></i> <%= deliveryTime %>-<%= deliveryTime + 10 %> mins prep time</span>
                    <span class="bullet">•</span>
                    <span><i class="fa-solid fa-shield-halved"></i> Safety Standard Certified</span>
                </div>
            </div>
            <div class="action-buttons">
                <button class="btn-outline"><i class="fa-regular fa-heart"></i> Save</button>
                <button class="btn-outline"><i class="fa-solid fa-share-nodes"></i> Share</button>
            </div>
        </section>

        <!-- DYNAMIC SMART CATEGORY PROCESSING -->
        <%
            List<Menu> allmenuList = (List<Menu>) request.getAttribute("allmenu");
            Map<String, Integer> dbCategories = new LinkedHashMap<>();
            int totalItems = (allmenuList != null) ? allmenuList.size() : 0;

            if (allmenuList != null) {
                for (Menu m : allmenuList) {
                    String cat = m.getCategory();
                    String itemName = (m.getItemName() != null) ? m.getItemName().toLowerCase() : "";

                    // Safe evaluation if category is missing or null
                    if (cat == null || cat.trim().isEmpty()) {
                        if (itemName.contains("pizza")) cat = "Pizza";
                        else if (itemName.contains("pasta")) cat = "Pasta";
                        else if (itemName.contains("grill") || itemName.contains("bbq")) cat = "Grill";
                        else if (itemName.contains("biryani")) cat = "Biryani";
                        else if (itemName.contains("burger")) cat = "Burger";
                        else if (itemName.contains("ice") || itemName.contains("dessert")) cat = "Dessert";
                        else cat = "Main Course";
                        
                        m.setCategory(cat); // Assign back so data-category on card matches exactly
                    } else {
                        cat = cat.trim();
                    }

                    dbCategories.put(cat, dbCategories.getOrDefault(cat, 0) + 1);
                }
            }
        %>

        <!-- Main Layout -->
        <div class="main-layout">
            
            <!-- Left Sidebar Categories -->
            <aside class="sidebar">
                <h3>Menu Categories</h3>
                <ul class="category-list">
                    <li class="category-item active" data-category="all">
                        <span><i class="fa-solid fa-fire"></i> All Items</span>
                        <span class="count"><%= totalItems %></span>
                    </li>
                    
                    <% 
                        for (Map.Entry<String, Integer> entry : dbCategories.entrySet()) {
                    %>
                            <li class="category-item" data-category="<%= entry.getKey().toLowerCase() %>">
                                <span><i class="fa-solid fa-utensils"></i> <%= entry.getKey() %></span>
                                <span class="count"><%= entry.getValue() %></span>
                            </li>
                    <% 
                        } 
                    %>
                </ul>

                <div class="delivery-promo">
                    <i class="fa-solid fa-truck-fast"></i>
                    <p><strong>Complimentary Express Delivery</strong></p>
                    <p class="sub-text">Applied automatically on orders over ₹199</p>
                </div>
            </aside>

            <!-- Main Menu Area -->
            <main class="menu-content">
                
                <!-- Category Filters Horizontal Pills -->
                <div class="filter-section">
                    <button class="pill active" data-category="all">All Items</button>
                    <% 
                        for (String dbCat : dbCategories.keySet()) {
                    %>
                            <button class="pill" data-category="<%= dbCat.toLowerCase() %>"><%= dbCat %></button>
                    <% 
                        } 
                    %>
                </div>

                <!-- Section Header -->
                <div class="section-header">
                    <h2 class="section-title">All Items</h2>
                </div>

                <!-- Food Cards Grid -->
                <div class="food-grid">
                    <%
                        if (allmenuList != null && !allmenuList.isEmpty()) {
                            for (Menu menu : allmenuList) {
                                String itemCat = (menu.getCategory() != null) ? menu.getCategory().trim().toLowerCase() : "main course";
                    %>
                                <div class="card food-card" data-category="<%= itemCat %>">
                                    <div class="card-img-container">
                                        <img src="${pageContext.request.contextPath}/<%= menu.getImagepath() %>" alt="<%= menu.getItemName() %>" onerror="this.onerror=null; this.src='https://via.placeholder.com/300x200?text=Food+Image';">
                                    </div>
                                    <div class="card-body">
                                        <div class="title-row">
                                            <span class="item-name"><%= menu.getItemName() %></span>
                                            
                                            <% 
                                                String nameLower = (menu.getItemName() != null) ? menu.getItemName().toLowerCase() : "";
                                                if (nameLower.contains("veg") || nameLower.contains("paneer") || nameLower.contains("jamun") || nameLower.contains("ice") || nameLower.contains("dosa") || nameLower.contains("scoop")) {
                                            %>
                                                <div class="diet-badge veg" title="Vegetarian"><div class="dot"></div></div>
                                            <% } else { %>
                                                <div class="diet-badge non-veg" title="Non-Vegetarian"><div class="dot"></div></div>
                                            <% } %>
                                        </div>
                                        <p class="item-desc"><%= menu.getDescription() %></p>
                                        <div class="card-footer">
                                            <div class="rating-price">
                                                <span class="rating"><%= menu.getRating() %> ★</span>
                                                <span class="price">₹<%= menu.getPrice() %></span>
                                            </div>

                                            <!-- ADD TO CART FORM -->
                                            <form action="callCartServlet" method="POST" style="margin: 0;">
                                                <input type="hidden" name="itemId" value="<%= menu.getMenuid() %>">
                                                <input type="hidden" name="restaurantId" value="<%= menu.getResturantid() %>">
                                                <input type="hidden" name="quantity" value="1">
                                                <input type="hidden" name="action" value="add">
                                                <button type="submit" class="add-btn">+ ADD</button>
                                            </form>

                                        </div>
                                    </div>
                                </div>
                    <%
                            }
                        } else {
                    %>
                        <p style="color: #9ca3af; grid-column: 1 / -1; padding: 20px 0;">No menu items available for this restaurant.</p>
                    <%
                        }
                    %>
                </div>

            </main>
        </div>
    </div>

    <!-- Floating Cart View Bar -->
    <div class="cart-banner">
        <div class="cart-details">
            <div class="cart-icon-wrapper">
                <i class="fa-solid fa-basket-shopping"></i>
            </div>
            <div>
                <div class="cart-title">Items in Cart</div>
                <div class="cart-subtotal">Ready to order your delicious meal?</div>
            </div>
        </div>
        <a href="Cart.jsp" class="btn-view-cart" style="text-decoration: none; color: white;">View Cart <i class="fa-solid fa-arrow-right"></i></a>
    </div>

    <!-- JAVASCRIPT: REAL-TIME FILTERING & SEARCH -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const searchInput = document.getElementById("menuSearchInput");
            const categoryItems = document.querySelectorAll(".category-item");
            const pills = document.querySelectorAll(".pill");
            const foodCards = document.querySelectorAll(".food-card");

            function filterByCategory(selectedCategory) {
                const targetCat = selectedCategory.toLowerCase().trim();

                foodCards.forEach(card => {
                    const cardCat = card.getAttribute("data-category") ? card.getAttribute("data-category").toLowerCase().trim() : "";

                    if (targetCat === "all" || targetCat === "all items") {
                        card.style.display = "flex";
                    } else if (cardCat === targetCat) {
                        card.style.display = "flex";
                    } else {
                        card.style.display = "none";
                    }
                });
            }

            // Real-time Search
            if (searchInput) {
                searchInput.addEventListener("keyup", function () {
                    const query = searchInput.value.toLowerCase().trim();

                    foodCards.forEach(card => {
                        const name = card.querySelector(".item-name") ? card.querySelector(".item-name").textContent.toLowerCase() : "";
                        const desc = card.querySelector(".item-desc") ? card.querySelector(".item-desc").textContent.toLowerCase() : "";

                        if (name.includes(query) || desc.includes(query)) {
                            card.style.display = "flex";
                        } else {
                            card.style.display = "none";
                        }
                    });
                });
            }

            // Left Sidebar Category Click
            categoryItems.forEach(item => {
                item.addEventListener("click", function () {
                    categoryItems.forEach(i => i.classList.remove("active"));
                    this.classList.add("active");
                    const cat = this.getAttribute("data-category");
                    filterByCategory(cat);
                });
            });

            // Horizontal Category Pill Click
            pills.forEach(pill => {
                pill.addEventListener("click", function () {
                    pills.forEach(p => p.classList.remove("active"));
                    this.classList.add("active");
                    const cat = this.getAttribute("data-category") || this.textContent;
                    filterByCategory(cat);
                });
            });
        });
    </script>

</body>
</html>