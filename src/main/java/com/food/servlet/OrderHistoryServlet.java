package com.food.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.food.DAOImpl.OrderDAOImpl;
import com.food.model.OrderHistoryDAO;
import com.food.model.User;

@WebServlet("/orderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        // 1. Fetch logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            user = (User) session.getAttribute("user");
        }

        // 2. Redirect to login if user is not authenticated
        if (user == null) {
            resp.sendRedirect("login.html");
            return;
        }

        // 3. Fetch past orders with restaurant and item details using user's ID
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        List<OrderHistoryDAO> orderList = orderDAO.getOrderHistoryDetailsByUser(user.getUserId());

        // 4. Attach list to request and forward to JSP
        req.setAttribute("orderList", orderList);
        RequestDispatcher rd = req.getRequestDispatcher("orderHistory.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}