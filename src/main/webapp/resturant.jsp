<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.food.model.Resturant" %>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodZone | Best Restaurants</title>
    <link rel="stylesheet" href="resturant1.css">

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
</head>

<body>

    <!-- =====================================================
         NAVBAR
    ====================================================== -->
    <header class="navbar">
        <div class="logo">
            <span class="logo-icon">🍔</span>
            <span>FoodZone</span>
        </div>

        <!-- SEARCH CONTAINER WITH UPDATED ID -->
        <div class="search-container">
            <span class="search-icon">🔍</span>
            <input type="text" id="searchInput" class="search" placeholder="Search restaurants, cuisines or dishes...">
        </div>

        <nav>
            <a href="callResturantservlet" class="active">Home</a>
            <a href="login.html">Login</a>
            <a href="register.html">Sign Up</a>
            <a href="profile.jsp">Profile</a>
            <a href="logoutServlet" style="color: #f87171;">Logout</a>
        </nav>
    </header>


    <!-- =====================================================
         HERO SECTION
    ====================================================== -->
    <section class="hero">

        <div class="hero-content">
            <div class="small-title">
                <span>🍽️</span>
                <span>Welcome to FoodZone</span>
            </div>

            <h1>
                Discover the Best
                <span>Food Near You</span>
            </h1>

            <p>
                Order from your favourite restaurants and enjoy
                delicious food delivered fresh and fast to your
                doorstep.
            </p>

            <div class="hero-buttons">
                <a href="#restaurants" class="order-btn">
                    Order Now
                    <span>→</span>
                </a>
                <a href="#restaurants" class="explore-btn">
                    Explore Restaurants
                </a>
            </div>

            <div class="hero-features">
                <div>
                    <span>🚚</span>
                    <strong>Fast Delivery</strong>
                </div>
                <div>
                    <span>⭐</span>
                    <strong>Best Quality</strong>
                </div>
                <div>
                    <span>🏷️</span>
                    <strong>Great Offers</strong>
                </div>
            </div>
        </div>

        <div class="hero-video">
            <video autoplay muted loop playsinline>
                <source src="Videos/Burger-AI-Video.mp4" type="video/mp4">
                Your browser does not support the video tag.
            </video>

            <div class="video-overlay">
                <div class="video-label">
                    🔥 Delicious Food
                </div>
            </div>
        </div>

    </section>


    <!-- =====================================================
         RESTAURANTS SECTION
    ====================================================== -->
    <section class="restaurants-section" id="restaurants">

        <div class="section-heading">
            <div>
                <span class="section-tag">EXPLORE NOW</span>
                <h2>Popular <span>Restaurants</span></h2>
                <p>Discover the best restaurants around Bangalore</p>
            </div>
            <a href="#" class="view-all-btn">View All →</a>
        </div>


        <!-- RESTAURANT GRID (Dynamic Database Loop) -->
        <div class="restaurant-grid">
        
        <%
            // 1. Retrieve list from request attribute attached by Resturantservlet
            List<Resturant> allResturants = (List<Resturant>) request.getAttribute("allResturants");
            
            // 2. Check if list has database records
            if (allResturants != null && !allResturants.isEmpty()) {
                
                // 3. Loop through database entries
                for (Resturant resturant : allResturants) {
        %>
                    <!-- Restaurant Card -->
                    <div class="restaurant-card">
                        <div class="restaurant-image">
                            <!-- Dynamically loads imagepath from DB -->
                            <img src="${pageContext.request.contextPath}/<%= resturant.getImagePath() %>" 
                                 alt="<%= resturant.getName() %>"
                                 onerror="this.onerror=null; this.src='https://via.placeholder.com/300x200?text=Restaurant+Image';">
                            <span class="heart">♡</span>
                        </div>
                        <div class="restaurant-info">
                            
                            <h3><%= resturant.getName() %></h3>
                            <p class="cuisine"><%= resturant.getCuisineType() %></p>
                            
                            <div class="restaurant-meta">
                                <span class="rating">⭐ <%= resturant.getRating() %></span>
                                <span>🛵 <%= resturant.getDeliveryTime() %> mins</span>
                            </div>
                            
                            <div class="location">📍 <%= resturant.getAddress() %></div>
                            
                            <!-- Route directly to /menu servlet with resturantID & resturantName -->
                            <a href="menu?resturantID=<%= resturant.getResturantId() %>&resturantName=<%= resturant.getName() %>" class="menu-btn">View Menu →</a>
                        </div>
                    </div>
        <% 
                } // End loop
            } else {
        %>
                <p style="color: #fff; padding: 20px; font-size: 1.2rem; grid-column: 1 / -1;">No restaurants available at the moment.</p>
        <%
            }
        %>
        
        </div>

    </section>


    <!-- =====================================================
         FOOTER
    ====================================================== -->
    <footer class="foodzone-footer">
        <div class="footer-main">

            <div class="footer-brand">
                <div class="footer-logo">
                    <span class="logo-icon">🍔</span>
                    <span>FoodZone</span>
                </div>
                <p>
                    Delicious food from the best restaurants,
                    delivered fresh and fast to your doorstep.
                </p>
                <div class="footer-tagline">
                    🍴 <span>Eat Better. Order Faster.</span>
                </div>
            </div>

            <div class="footer-section">
                <h3>FoodZone</h3>
                <a href="about.html">About Us</a>
                <a href="our-story.html">Our Story</a>
                <a href="careers.html">Careers</a>
                <a href="partner.html">Partner With Us</a>
            </div>

            <div class="footer-section">
                <h3>Explore</h3>
                <a href="#restaurants">Restaurants</a>
                
                <a href="#">Popular Food</a>
                <a href="orderHistory.jsp">My Orders</a>
            </div>

            <div class="footer-section">
                <h3>What We Offer</h3>
                <a href="#">🍕 Wide Food Selection</a>
                <a href="#">🚀 Fast Delivery</a>
                <a href="#">💰 Best Deals</a>
                <a href="#">⭐ Top Restaurants</a>
            </div>

            <div class="footer-section contact-section">
                <h3>Contact Us</h3>
                <div class="contact-row">
                    <span>📍</span>
                    <p>Bangalore, India</p>
                </div>
                <div class="contact-row">
                    <span>📞</span>
                    <p>+91 83091 12610</p>
                </div>
                <div class="contact-row">
                    <span>✉️</span>
                    <p>ashokdeva077@gmail.com</p>
                </div>
                <div class="contact-row">
                    <span>⏰</span>
                    <p>24 × 7 Support</p>
                </div>
            </div>

        </div>

        <div class="footer-bottom">
            <p>© 2026 <strong>FoodZone</strong>. All Rights Reserved @ASHOK DEVA.</p>
            <div class="footer-legal">
                <a href="privacy-policy.html">Privacy Policy</a>
                <span>•</span>
                <a href="terms.html">Terms & Conditions</a>
                <span>•</span>
                <a href="refund-policy.html">Refund Policy</a>
            </div>
        </div>
    </footer>

    <!-- =====================================================
         JAVASCRIPT REAL-TIME SEARCH FILTER
    ====================================================== -->
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const searchInput = document.getElementById("searchInput");
            const cards = document.querySelectorAll(".restaurant-card");

            if (searchInput) {
                searchInput.addEventListener("keyup", function () {
                    const query = searchInput.value.toLowerCase().trim();

                    cards.forEach(card => {
                        const nameElement = card.querySelector("h3");
                        const cuisineElement = card.querySelector(".cuisine");

                        const name = nameElement ? nameElement.textContent.toLowerCase() : "";
                        const cuisine = cuisineElement ? cuisineElement.textContent.toLowerCase() : "";

                        if (name.includes(query) || cuisine.includes(query)) {
                            card.style.display = "block";
                        } else {
                            card.style.display = "none";
                        }
                    });
                });
            }
        });
    </script>
    
    <!-- SweetAlert2 Library & Success Popup for resturant.jsp -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
    @keyframes pulseSuccess {
        0%, 100% {
            opacity: 1;
            transform: scale(1);
            filter: drop-shadow(0 0 4px rgba(46, 204, 113, 0.4));
        }
        50% {
            opacity: 0.3;
            transform: scale(1.12);
            filter: drop-shadow(0 0 16px rgba(46, 204, 113, 0.9));
        }
    }
    .swal2-icon.swal2-success {
        animation: pulseSuccess 1.2s infinite ease-in-out !important;
        border-color: #2ecc71 !important;
    }
    .swal2-icon.swal2-success .swal2-success-line-tip,
    .swal2-icon.swal2-success .swal2-success-line-long {
        background-color: #2ecc71 !important;
    }
</style>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const urlParams = new URLSearchParams(window.location.search);

        if (urlParams.get("login") === "success") {
            Swal.fire({
                title: "Login Successful!",
                text: "Welcome back to FoodZone! Discover top dishes near you.",
                icon: "success",
                confirmButtonColor: "#ff493d",
                confirmButtonText: "Explore Now",
                background: "#141821",
                color: "#ffffff"
            });
            window.history.replaceState({}, document.title, window.location.pathname);
        }
    });
</script>
    
    
    
    
    
    
    

</body>
</html>