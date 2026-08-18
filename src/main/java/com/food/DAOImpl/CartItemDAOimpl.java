package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.CartItemDAO;
import com.food.model.CartItem;
import com.tap.utility.DBConnection;

public class CartItemDAOimpl implements CartItemDAO {

	@Override
	public void addCartItem(CartItem cartItem) {
		String INSERT_QUERY = "INSERT INTO cartitem(cartId,menuId,quantity) VALUES(?,?,?)";
		Connection con=DBConnection.getConnection();
		try {
			PreparedStatement stmt=con.prepareStatement(INSERT_QUERY);
            stmt.setInt(1, cartItem.getCartId());
            stmt.setInt(2, cartItem.getMenuId());
            stmt.setInt(3, cartItem.getQuantity());
            int rows=stmt.executeUpdate();
            if(rows>0) {
            	System.out.println("Cart Item Added Successfully!");
            }else {
            	System.out.println("Cart Item Not Added");
            }

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public CartItem getCartItem(int cartItemId) {
		
		 String SELECT_QUERY = "SELECT * FROM cartitem WHERE cartItemId=?";
		 Connection con = DBConnection.getConnection();
	        CartItem cartItem = null;
	        try {
	            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
	            stmt.setInt(1, cartItemId);
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()) {
	                cartItem = new CartItem();
	                cartItem.setCartItemId(rs.getInt("cartItemId"));
	                cartItem.setCartId(rs.getInt("cartId"));
	                cartItem.setMenuId(rs.getInt("menuId"));
	                cartItem.setQuantity(rs.getInt("quantity"));
	            }

			} catch (Exception e) {
				e.printStackTrace();
			}
	
		return cartItem;
	}

	@Override
	public void updateCartItem(CartItem cartItem) {
		 String UPDATE_QUERY = "UPDATE cartitem SET cartId=?,menuId=?,quantity=? WHERE cartItemId=?";
		   Connection con = DBConnection.getConnection();
		   try {
	            PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY);
	            stmt.setInt(1, cartItem.getCartId());
	            stmt.setInt(2, cartItem.getMenuId());
	            stmt.setInt(3, cartItem.getQuantity());
	            stmt.setInt(4, cartItem.getCartItemId());
	            int rows = stmt.executeUpdate();
	            if (rows > 0) {
	                System.out.println("Cart Item Updated Successfully!");
	            } else {
	                System.out.println("Cart Item Not Found!");
	            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCartItem(int cartItemId) {
        String DELETE_QUERY = "DELETE FROM cartitem WHERE cartItemId=?";
        Connection con = DBConnection.getConnection();
        try {
        	
            PreparedStatement stmt = con.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, cartItemId);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Cart Item Deleted Successfully!");
            } else {
                System.out.println("Cart Item Not Found!");
            }

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public List<CartItem> getAllCartItems() {
		
        String SELECT_QUERY = "SELECT * FROM cartitem";
        Connection con = DBConnection.getConnection();
        List<CartItem> cartItemList = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
            ResultSet rs = stmt.executeQuery();
           while(rs.next()) {
               CartItem cartItem = new CartItem();
               cartItem.setCartItemId(rs.getInt("cartItemId"));
               cartItem.setCartId(rs.getInt("cartId"));
               cartItem.setMenuId(rs.getInt("menuId"));
               cartItem.setQuantity(rs.getInt("quantity"));
               cartItemList.add(cartItem);
           }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

}
