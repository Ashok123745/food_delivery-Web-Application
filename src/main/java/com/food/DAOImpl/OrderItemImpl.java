package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.OrderItemDAO;
import com.food.model.OrderItem;
import com.tap.utility.DBConnection;

public class OrderItemImpl implements OrderItemDAO {

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String INSERT_QUERY = "INSERT INTO orderitem(orderId, menuId, quantity, price) VALUES(?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();        
        try {
            PreparedStatement stmt = con.prepareStatement(INSERT_QUERY);
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getMenuId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getPrice());
            
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Order Item Added Successfully!");
            } else {
                System.out.println("Order Item Not Added!");
            }    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        String SELECT_QUERY = "SELECT * FROM orderitem WHERE orderItemId=?";
        Connection con = DBConnection.getConnection();    
        OrderItem orderItem = null;
        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
            stmt.setInt(1, orderItemId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderItem = new OrderItem();
                orderItem.setOrderItemId(rs.getInt("orderItemId"));
                orderItem.setOrderId(rs.getInt("orderId"));
                orderItem.setMenuId(rs.getInt("menuId"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        String UPDATE_QUERY = "UPDATE orderitem SET orderId=?, menuId=?, quantity=?, price=? WHERE orderItemId=?";
        Connection con = DBConnection.getConnection();    
        try {
            PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY);
            stmt.setInt(1, orderItem.getOrderId());
            stmt.setInt(2, orderItem.getMenuId());
            stmt.setInt(3, orderItem.getQuantity());
            stmt.setDouble(4, orderItem.getPrice());
            stmt.setInt(5, orderItem.getOrderItemId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Order Item Updated Successfully!");
            } else {
                System.out.println("Order Item Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        String DELETE_QUERY = "DELETE FROM orderitem WHERE orderItemId=?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, orderItemId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Order Item Deleted Successfully!");
            } else {
                System.out.println("Order Item Not Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        String SELECT_QUERY = "SELECT * FROM orderitem";
        Connection con = DBConnection.getConnection();
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(rs.getInt("orderItemId"));
                orderItem.setOrderId(rs.getInt("orderId"));
                orderItem.setMenuId(rs.getInt("menuId"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPrice(rs.getDouble("price"));
                orderItemList.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
    
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        String SELECT_BY_ORDER_QUERY = "SELECT * FROM orderitem WHERE orderId=?";
        Connection con = DBConnection.getConnection();
        List<OrderItem> itemList = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_BY_ORDER_QUERY);
            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("orderItemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setMenuId(rs.getInt("menuId"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price")); // Matches column 'price' in MySQL

                itemList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }
}