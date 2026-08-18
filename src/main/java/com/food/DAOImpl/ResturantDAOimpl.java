package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.ResturantDAO;
import com.food.model.Resturant;
import com.tap.utility.DBConnection;

public class ResturantDAOimpl implements ResturantDAO{

	@Override
	public void addResturant(Resturant resturant) {
		String INSERT_QUERY = "INSERT INTO resturant(name,cuisineType,deliveryTime,address,"
		        + "adminuserId,rating,isActive,imagepath) VALUES(?,?,?,?,?,?,?,?)";
		
	Connection	con = DBConnection.getConnection();
  try {
	PreparedStatement stmt  = con.prepareStatement(INSERT_QUERY);
	stmt.setString(1, resturant.getName());
	stmt.setString(2, resturant.getCuisineType());
	stmt.setInt(3, resturant.getDeliveryTime());
	stmt.setString(4, resturant.getAddress());
	stmt.setInt(5, resturant.getAdminUserId());
	stmt.setDouble(6, resturant.getRating());
	stmt.setBoolean(7, resturant.isActive());
	stmt.setString(8,resturant.getImagePath());
	
	
	int rows =stmt.executeUpdate();

    if (rows > 0) {
        System.out.println("Restaurant Added Successfully");
    } else {
        System.out.println("Failed to Add Restaurant");
    }	
} catch (SQLException e) {
	e.printStackTrace();
}
		
		
	}

	@Override
	public Resturant getResturant(int ResturantId) {
		  String SELECT_QUERY = "SELECT * FROM resturant WHERE idResturant =?";
		  Connection con=DBConnection.getConnection();
		  Resturant resturant=null;
		try {  
			PreparedStatement stmt  = con.prepareStatement(SELECT_QUERY);
			stmt.setInt(1,ResturantId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("idResturant");
				String name = rs.getString("name");
				String cuisineType=rs.getString("cuisineType");
				int deliveryTime=rs.getInt("deliveryTime");
				String address=rs.getString("address");
				int adminUserId=rs.getInt("adminuserId");
				double rating=rs.getDouble("rating");
				boolean isActive=rs.getBoolean("isActive");
				String imagepath=rs.getString("imagepath");
				
	   resturant = new Resturant(name,cuisineType,deliveryTime,address,adminUserId,rating,isActive,imagepath);	
	   resturant.setResturantId(id);
			}
	        rs.close();
	        stmt.close();
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
		
		return resturant;
	}

	@Override
	public void UpdateResturant(Resturant Resturant) {
		String UPDATE_QUERY =" UPDATE resturant SET name=?,cuisineType=?,deliveryTime=?,"
				+ "address=?,adminuserId=?,rating=?,isActive=?,imagepath=? WHERE idResturant=?";
	Connection	con=DBConnection.getConnection();

	try 
	{
		    PreparedStatement stmt=con.prepareStatement(UPDATE_QUERY);
			stmt.setString(1, Resturant.getName());
			stmt.setString(2, Resturant.getCuisineType());
			stmt.setInt(3, Resturant.getDeliveryTime());
			stmt.setString(4, Resturant.getAddress());
			stmt.setInt(5, Resturant.getAdminUserId());
			stmt.setDouble(6, Resturant.getRating());
			stmt.setBoolean(7, Resturant.isActive());
			stmt.setString(8,Resturant.getImagePath());
			stmt.setInt(9, Resturant.getResturantId());
			int rows=stmt.executeUpdate();
			if(rows > 0) {
				System.out.println("Resturant Details UPDATED Successfully!");
			}else {
				System.out.println("Sorry Resturant Details Not Found");
			}
			
	}catch(SQLException e){
		e.printStackTrace();
	}
				
	}

	@Override
	public void DeleteResturant(int ResturantId) {
		 String DELETE_QUERY= "DELETE FROM resturant WHERE idResturant=?";
		Connection  con=DBConnection.getConnection();
		try {
			PreparedStatement stmt=con.prepareStatement(DELETE_QUERY);
	        stmt.setInt(1, ResturantId); 
	        int rows = stmt.executeUpdate();
	        if(rows > 0) {
	        	System.out.println("Resturant Deleted Successfully" );
	        }else {
	        	System.out.println("Sorry Resturant Not Found");
	        }
	        		
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public List<Resturant> getAllResturant() {
		 String SELECT_QUERY= "SELECT*FROM resturant";
		 Connection con=DBConnection.getConnection();
		 List<Resturant> resturantList = new ArrayList<>();
		 try {
			 PreparedStatement stmt=con.prepareStatement(SELECT_QUERY);
			 ResultSet rs=stmt.executeQuery();
			 while(rs.next()) 
			 {
				 Resturant resturant = new Resturant();

		            resturant.setResturantId(rs.getInt("idResturant"));
		            resturant.setName(rs.getString("name"));
		            resturant.setCuisineType(rs.getString("cuisineType"));
		            resturant.setDeliveryTime(rs.getInt("deliveryTime"));
		            resturant.setAddress(rs.getString("address"));
		            resturant.setAdminuserId(rs.getInt("adminuserId"));
		            resturant.setRating(rs.getDouble("rating"));
		            resturant.setActive(rs.getBoolean("isActive"));
		            resturant.setImagePath(rs.getString("imagepath"));
		            
		            resturantList.add(resturant);
			 }
			 
			
		} catch (Exception e) {
			
		}
		return resturantList;
	}

}
