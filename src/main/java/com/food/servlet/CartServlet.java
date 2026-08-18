package com.food.servlet;

import java.io.IOException;

import com.food.DAOImpl.MenuDAOimpl;
import com.food.model.Cart1;
import com.food.model.Cartitem1;
import com.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/callCartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart1 cart = (Cart1) session.getAttribute("cart");
        Integer sessionRestaurantId = (Integer) session.getAttribute("resturantId");
        
        String reqRestaurantId = req.getParameter("restaurantID");
        if (reqRestaurantId == null) {
            reqRestaurantId = req.getParameter("restaurantId");
        }
        if (reqRestaurantId == null) {
            reqRestaurantId = req.getParameter("resturantId");
        }

        if (reqRestaurantId != null && !reqRestaurantId.trim().isEmpty()) {
            try {
                int newRestaurantId = Integer.parseInt(reqRestaurantId.trim());
                if (cart == null || sessionRestaurantId == null || sessionRestaurantId != newRestaurantId) {
                    cart = new Cart1();
                    session.setAttribute("cart", cart);
                    session.setAttribute("resturantId", newRestaurantId);
                }
            } catch (NumberFormatException e) {
                // Ignore invalid format
            }
        } else if (cart == null) {
            cart = new Cart1();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        if (action != null) {
            if (action.equalsIgnoreCase("add")) {
                addItemToCart(req, cart);
            } else if (action.equalsIgnoreCase("update")) {
                updateItemToCart(req, cart);
            } else if (action.equalsIgnoreCase("delete")) {
                deleteItemFromCart(req, cart);
            }
        }
        
        resp.sendRedirect("Cart.jsp");
    }

    private void deleteItemFromCart(HttpServletRequest req, Cart1 cart) {
        String menuIdParam = req.getParameter("menuId");
        if (menuIdParam == null) {
            menuIdParam = req.getParameter("itemId");
        }
        if (menuIdParam != null && !menuIdParam.trim().isEmpty()) {
            try {
                int menuId = Integer.parseInt(menuIdParam.trim());
                cart.removeItem(menuId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateItemToCart(HttpServletRequest req, Cart1 cart) {
        String menuIdParam = req.getParameter("menuId");
        if (menuIdParam == null) {
            menuIdParam = req.getParameter("itemId");
        }
        String quantityParam = req.getParameter("quantity");

        if (menuIdParam != null && quantityParam != null && !menuIdParam.trim().isEmpty() && !quantityParam.trim().isEmpty()) {
            try {
                int menuId = Integer.parseInt(menuIdParam.trim());
                int quantity = Integer.parseInt(quantityParam.trim());
                cart.updateItem(menuId, quantity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void addItemToCart(HttpServletRequest req, Cart1 cart) {
        String menuIdParam = req.getParameter("menuId");
        if (menuIdParam == null) {
            menuIdParam = req.getParameter("itemId");
        }
        String quantityParam = req.getParameter("quantity");

        if (menuIdParam != null && !menuIdParam.trim().isEmpty()) {
            try {
                int menuId = Integer.parseInt(menuIdParam.trim());
                int quantity = (quantityParam != null && !quantityParam.trim().isEmpty()) 
                                ? Integer.parseInt(quantityParam.trim()) 
                                : 1;
                
                MenuDAOimpl menuDAOImpl = new MenuDAOimpl();
                Menu menu = menuDAOImpl.getMenu(menuId);
                
                if (menu != null) {
                    Cartitem1 cartItem = new Cartitem1(
                        menu.getMenuid(),
                        menu.getResturantid(),
                        menu.getItemName(), 
                        menu.getPrice(), 
                        quantity,
                        menu.getImagepath() // Using your exact method name
                    );
                    cart.addItem(cartItem);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}