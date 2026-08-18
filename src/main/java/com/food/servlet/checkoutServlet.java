package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.DAOImpl.OrderDAOImpl;
import com.food.DAOImpl.OrderItemImpl;
import com.food.model.Cart1;
import com.food.model.Cartitem1;
import com.food.model.Order;
import com.food.model.OrderItem;
import com.food.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkoutServlet")
public class checkoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        // 1. Fetch User (Checks 'loggedInUser' from loginservlet first)
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            user = (User) session.getAttribute("user");
        }

        // 2. Redirect to login if user is not logged in
        if (user == null) {
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.forward(req, resp);
            return;
        }

        // 3. Fetch Cart from Session
        Cart1 cart = (Cart1) session.getAttribute("cart");
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("Cart.jsp");
            return;
        }

        // 4. Fetch Restaurant ID
        Integer restaurantId = (Integer) session.getAttribute("resturantId");
        if (restaurantId == null && req.getParameter("restaurantId") != null) {
            try {
                restaurantId = Integer.parseInt(req.getParameter("restaurantId").trim());
            } catch (NumberFormatException e) {
                restaurantId = 1;
            }
        }

        // 5. Fetch or Calculate Final Amount
        double finalAmount = 0.0;
        if (req.getParameter("amount") != null && !req.getParameter("amount").trim().isEmpty()) {
            try {
                finalAmount = Double.parseDouble(req.getParameter("amount").trim());
            } catch (NumberFormatException e) {
                finalAmount = 0.0;
            }
        } else if (session.getAttribute("finalAmount") != null) {
            finalAmount = Double.parseDouble(session.getAttribute("finalAmount").toString());
        }

        // Fallback: calculate sum directly from cart items if amount is 0
        if (finalAmount <= 0.0) {
            for (Cartitem1 item : cart.getItems().values()) {
                finalAmount += item.getTotalPrice();
            }
        }

        // 6. Fetch Payment Mode
        String paymentMode = req.getParameter("paymentMode");
        if (paymentMode == null || paymentMode.trim().isEmpty()) {
            paymentMode = "Cash on Delivery";
        }

        // 7. Create and Insert Order
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setResturantId(restaurantId != null ? restaurantId : 1);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setPaymentMode(paymentMode);
        order.setStatus("PLACED");
        order.setTotalAmount(finalAmount);

        OrderDAOImpl orderDAOImpl = new OrderDAOImpl();
        int orderId = orderDAOImpl.addOrder(order);

        // 8. Insert each cart item into orderitem table
        OrderItemImpl orderItemDAOImpl = new OrderItemImpl();
        List<Cartitem1> orderedItemsList = new ArrayList<>(cart.getItems().values());

        for (Cartitem1 item : orderedItemsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuId(item.getMenuId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getTotalPrice());

            orderItemDAOImpl.addOrderItem(orderItem);
        }

        // 9. Pass attributes to confirmation page BEFORE removing from session
        req.setAttribute("orderId", orderId);
        req.setAttribute("orderedItems", orderedItemsList);
        req.setAttribute("totalAmount", finalAmount);
        req.setAttribute("paymentMode", paymentMode);

        // 10. Clean up session attributes
        session.removeAttribute("cart");
        session.removeAttribute("resturantId");
        session.removeAttribute("finalAmount");

        // 11. Forward to confirmation JSP
        RequestDispatcher rd = req.getRequestDispatcher("orderConfirmation.jsp");
        rd.forward(req, resp);
    }
}