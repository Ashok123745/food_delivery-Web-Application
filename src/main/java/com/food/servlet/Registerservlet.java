package com.food.servlet;

import java.io.IOException;
import java.net.Authenticator.RequestorType;

import org.mindrot.jbcrypt.BCrypt;

import com.food.DAOImpl.UserDAOimpl;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/callRegisterServlet")

public class Registerservlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		
		
		String hashpw = BCrypt.hashpw(password,BCrypt.gensalt(9));
	

		// Create user object using the new constructor
		User user = new User(username, hashpw, email, mobile, address, role);

		// Save to DB
		UserDAOimpl userDAO = new UserDAOimpl();
		int res =userDAO.addUser(user);
		
		if(res == 1) 
		{
			resp.sendRedirect("login.html?status=registered");
		}else {
			
			resp.sendRedirect("register.html?status=failed");
		}
		
		
	}

}
