package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.MenuDAO;
import com.food.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDAOimpl implements MenuDAO {

    @Override
    public void addMenu(Menu menu) {
        String INSERT_QUERY = "INSERT INTO menu(resturantid, itemName, description, price, isAvailable, "
                + "rating, imagepath, category) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(INSERT_QUERY);

            stmt.setInt(1, menu.getResturantid());
            stmt.setString(2, menu.getItemName());
            stmt.setString(3, menu.getDescription());
            stmt.setDouble(4, menu.getPrice());
            stmt.setBoolean(5, menu.isAvaiable());
            stmt.setDouble(6, menu.getRating());
            stmt.setString(7, menu.getImagepath());
            stmt.setString(8, menu.getCategory());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Menu Added Successfully");
            } else {
                System.out.println("Menu Not Added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, con);
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        String SELECT_QUERY = "SELECT * FROM menu WHERE Menuid = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Menu menu = null;

        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(SELECT_QUERY);
            stmt.setInt(1, menuId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                menu = extractMenuFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, con);
        }

        return menu;
    }

    @Override
    public void updateMenu(Menu menu) {
        String UPDATE_QUERY = "UPDATE menu SET resturantid=?, itemName=?, description=?, price=?, "
                + "isAvailable=?, rating=?, imagepath=?, category=? WHERE Menuid=?";
        
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(UPDATE_QUERY);

            stmt.setInt(1, menu.getResturantid());
            stmt.setString(2, menu.getItemName());
            stmt.setString(3, menu.getDescription());
            stmt.setDouble(4, menu.getPrice());
            stmt.setBoolean(5, menu.isAvaiable());
            stmt.setDouble(6, menu.getRating());
            stmt.setString(7, menu.getImagepath());
            stmt.setString(8, menu.getCategory());
            stmt.setInt(9, menu.getMenuid());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Menu Updated Successfully!");
            } else {
                System.out.println("Menu Not Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, con);
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String DELETE_QUERY = "DELETE FROM menu WHERE Menuid=?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, menuId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Menu Deleted Successfully!");
            } else {
                System.out.println("Menu Not Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, con);
        }
    }

    @Override
    public List<Menu> getAllMenu() {
        String SELECT_QUERY = "SELECT * FROM menu";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Menu> menuList = new ArrayList<>();

        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(SELECT_QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                menuList.add(extractMenuFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, con);
        }

        return menuList;
    }

    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId) {
        String SELECT_BY_RESTAURANT = "SELECT * FROM menu WHERE resturantid = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Menu> menuList = new ArrayList<>();

        try {
            con = DBConnection.getConnection();
            stmt = con.prepareStatement(SELECT_BY_RESTAURANT);
            stmt.setInt(1, restaurantId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                menuList.add(extractMenuFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, con);
        }

        return menuList;
    }

    private Menu extractMenuFromResultSet(ResultSet rs) throws Exception {
        Menu menu = new Menu();

        menu.setMenuid(rs.getInt("Menuid"));
        menu.setResturantid(rs.getInt("resturantid"));
        menu.setItemName(rs.getString("itemName"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));

        String isAvailStr = rs.getString("isAvailable");
        boolean isAvail = isAvailStr != null && (isAvailStr.equalsIgnoreCase("true") 
                || isAvailStr.equalsIgnoreCase("1") 
                || isAvailStr.equalsIgnoreCase("Available"));
        menu.setAvaiable(isAvail);

        menu.setRating(rs.getDouble("rating"));
        menu.setImagepath(rs.getString("imagepath"));

        try {
            menu.setCategory(rs.getString("category"));
        } catch (Exception e) {
            menu.setCategory("Main Course");
        }

        return menu;
    }

    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection con) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}