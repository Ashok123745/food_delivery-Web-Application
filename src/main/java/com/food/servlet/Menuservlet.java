package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.DAOImpl.MenuDAOimpl;
import com.food.DAOImpl.ResturantDAOimpl;
import com.food.model.Menu;
import com.food.model.Resturant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class Menuservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Parse restaurant ID safely
        String param = req.getParameter("resturantID");
        if (param == null || param.isEmpty()) {
            param = req.getParameter("restaurantID");
        }

        int resturantID = 1; // Default fallback
        if (param != null && !param.trim().isEmpty()) {
            try {
                resturantID = Integer.parseInt(param);
            } catch (NumberFormatException e) {
                resturantID = 1;
            }
        }

        // 2. Fetch specific Restaurant details from DB
        ResturantDAOimpl resturantDAOimpl = new ResturantDAOimpl();
        Resturant resturant = resturantDAOimpl.getResturant(resturantID);

        // 3. Fetch Menu items list from DB
        MenuDAOimpl menuDAOimpl = new MenuDAOimpl();
        List<Menu> allMenu = menuDAOimpl.getMenuByRestaurantId(resturantID);

        // 4. Set both attributes for menu.jsp
        req.setAttribute("currentResturant", resturant);
        req.setAttribute("allmenu", allMenu);

        // 5. Forward to menu.jsp
        RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}