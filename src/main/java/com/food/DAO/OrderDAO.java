package com.food.DAO;

import java.util.List;

import com.food.model.Order;
import com.food.model.OrderHistoryDAO;

public interface OrderDAO {
	
    int addOrder(Order order);

    Order getOrder(int orderId);

    void updateOrder(Order order);

    void deleteOrder(int orderId);

    List<Order> getAllOrders();
    List<Order> getAllOrdersByUser(int userId);
    List<OrderHistoryDAO> getOrderHistoryDetailsByUser(int userId);

}
