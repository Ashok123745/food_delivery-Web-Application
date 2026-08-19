package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.ResturantDAOimpl;
import com.food.model.Resturant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/callResturantServlet")
public class Resturantservlet extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. Fetch data from DAO
        ResturantDAOimpl resturantDAOimpl = new ResturantDAOimpl();
        List<Resturant> allResturants = resturantDAOimpl.getAllResturant();

        // 2. Print actual object values in Eclipse console for verification
        if (allResturants != null) {
            for (Resturant resturant : allResturants) {
                System.out.println("Fetched Restaurant: " + resturant.getName());
            }
        }

        // 3. Attach list to request object
        req.setAttribute("allResturants", allResturants);

        // 4. Forward to your JSP/HTML page
        RequestDispatcher dispatcher = req.getRequestDispatcher("resturant.jsp");
        dispatcher.forward(req, resp);
    }
}