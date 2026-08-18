package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.CartDAO;
import com.food.model.Cart;
import com.tap.utility.DBConnection;

public class CartDAOimpl implements CartDAO {

	@Override
	public void addCart(Cart cart) {
        String INSERT_QUERY = "INSERT INTO cart(userId) VALUES(?)";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(INSERT_QUERY);
            stmt.setInt(1, cart.getUserId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cart Added Successfully!");
            } else {
                System.out.println("Cart Not Added!");
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Cart getCart(int cartId) {
        String SELECT_QUERY = "SELECT * FROM cart WHERE cartId=?";
        Connection con = DBConnection.getConnection();
        Cart cart = null;
        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);

            stmt.setInt(1, cartId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setCartId(rs.getInt("cartId"));
                cart.setUserId(rs.getInt("userId"));
            }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart;
	}

	@Override
	public void updateCart(Cart cart) {
        String UPDATE_QUERY = "UPDATE cart SET userId=? WHERE cartId=?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY);
            stmt.setInt(1, cart.getUserId());
            stmt.setInt(2, cart.getCartId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cart Updated Successfully!");
            } else {
                System.out.println("Cart Not Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		
	

	@Override
	public void deleteCart(int cartId) {
        String DELETE_QUERY = "DELETE FROM cart WHERE cartId=?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, cartId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cart Deleted Successfully!");
            } else {
                System.out.println("Cart Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		
	

	@Override
	public List<Cart> getAllCarts() {
        String SELECT_QUERY = "SELECT * FROM cart";
        Connection con = DBConnection.getConnection();
        List<Cart> cartList = new ArrayList<>();
        try {
        	 PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
             ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                 Cart cart = new Cart();
                 cart.setCartId(rs.getInt("cartId"));
                 cart.setUserId(rs.getInt("userId"));
                 cartList.add(cart);
             }
        	
		} catch (Exception e) {
			 e.printStackTrace();
		}

		return cartList;
	}

}
