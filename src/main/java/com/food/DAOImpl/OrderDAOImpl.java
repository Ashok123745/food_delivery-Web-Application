package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.food.DAO.OrderDAO;
import com.food.model.Order;

import com.food.model.OrderHistoryDAO;
import com.tap.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public int addOrder(Order order) {
        int orderId = 0;
        String INSERT_QUERY = "INSERT INTO orders(userId, resturantId, orderDate, totalAmount, status, paymentMode) "
                            + "VALUES(?, ?, ?, ?, ?, ?)";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getResturantId());
            stmt.setTimestamp(3, order.getOrderDate());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());
            stmt.setString(6, order.getPaymentMode());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
                System.out.println("Order Added Successfully! Generated ID: " + orderId);
            } else {
                System.out.println("Order Not Added");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public Order getOrder(int orderId) {
        String SELECT_QUERY = "SELECT * FROM orders WHERE orderId = ?";
        Connection con = DBConnection.getConnection();
        Order order = null;

        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setResturantId(rs.getInt("resturantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void updateOrder(Order order) {
        String UPDATE_QUERY = "UPDATE orders SET userId=?, resturantId=?, orderDate=?, "
                            + "totalAmount=?, status=?, paymentMode=? WHERE orderId=?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY);

            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getResturantId());
            stmt.setTimestamp(3, order.getOrderDate());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());
            stmt.setString(6, order.getPaymentMode());
            stmt.setInt(7, order.getOrderId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Order Updated Successfully");
            } else {
                System.out.println("Order Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String DELETE_QUERY = "DELETE FROM orders WHERE orderId = ?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, orderId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Order Deleted Successfully");
            } else {
                System.out.println("Order Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        String SELECT_QUERY = "SELECT * FROM orders";
        Connection con = DBConnection.getConnection();
        List<Order> orderList = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setResturantId(rs.getInt("resturantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));

                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrdersByUser(int userId) {
        String SELECT_BY_USER_QUERY = "SELECT * FROM orders WHERE userId = ? ORDER BY orderDate DESC";
        Connection con = DBConnection.getConnection();
        List<Order> orderList = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(SELECT_BY_USER_QUERY);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setUserId(rs.getInt("userId"));
                order.setResturantId(rs.getInt("resturantId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setStatus(rs.getString("status"));
                order.setPaymentMode(rs.getString("paymentMode"));

                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    // Fetches complete order history with restaurant names and individual food items
    @Override
    public List<OrderHistoryDAO> getOrderHistoryDetailsByUser(int userId) {
        String query = "SELECT o.orderId, o.userId, o.orderDate, o.totalAmount, o.status, o.paymentMode, "
                     + "r.name AS restaurantName, "
                     + "m.itemName, oi.quantity, oi.price "
                     + "FROM orders o "
                     + "LEFT JOIN resturant r ON o.resturantId = r.idResturant "
                     + "LEFT JOIN orderitem oi ON o.orderId = oi.orderId "
                     + "LEFT JOIN menu m ON oi.menuId = m.Menuid "
                     + "WHERE o.userId = ? "
                     + "ORDER BY o.orderId DESC";

        List<OrderHistoryDAO> list = new ArrayList<>();
        Map<Integer, OrderHistoryDAO> orderMap = new LinkedHashMap<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");

                    OrderHistoryDAO history = orderMap.get(orderId);
                    if (history == null) {
                        history = new OrderHistoryDAO();
                        history.setOrderId(orderId);
                        history.setUserId(rs.getInt("userId"));
                        history.setOrderDate(rs.getTimestamp("orderDate"));
                        history.setTotalAmount(rs.getDouble("totalAmount"));
                        history.setStatus(rs.getString("status"));
                        history.setPaymentMode(rs.getString("paymentMode"));

                        String restName = rs.getString("restaurantName");
                        history.setRestaurantName(restName != null ? restName : "Restaurant");

                        orderMap.put(orderId, history);
                    }

                    String itemName = rs.getString("itemName");
                    if (itemName != null) {
                        history.addItem(itemName, rs.getInt("quantity"), rs.getDouble("price"));
                    }
                }
            }

            list.addAll(orderMap.values());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}