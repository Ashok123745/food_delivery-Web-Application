package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.food.DAO.UserDAO;
import com.food.DAOImpl.UserDAOimpl;
import com.food.model.User;

@WebServlet("/callLoginServlet")
public class loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Retrieve form inputs
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        UserDAO userDAO = new UserDAOimpl();

        // 2. Fetch user from database
        User user = userDAO.getUserByUsername(username);

        // 3. Verify user existence and password match
        if (user != null) {
            String dbPassword = user.getPassword();

            // Verify BCrypt hashed password
            if (BCrypt.checkpw(password, dbPassword)) {

                // 4. Update last login date in MySQL
                userDAO.updateLastLoginDate(user.getUserId());

                // 5. Update user object's timestamp in memory for this active session
                user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));

                // 6. Store user in session under both common keys to prevent lookup mismatches
                session.setAttribute("user", user);
                session.setAttribute("loggedInUser", user);
                session.setAttribute("UserName", user.getUsername());

                // 7. Redirect on success
                resp.sendRedirect("callResturantServlet?login=success");
            } else {
                // Password incorrect
                resp.sendRedirect("login.html?error=invalid_password");
            }
        } else {
            // User not found
            resp.sendRedirect("login.html?error=user_not_found");
        }
    }
}