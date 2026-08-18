package com.food.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAO {

    private int orderId;
    private int userId;
    private String restaurantName;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private String paymentMode;
    private List<OrderItemDetail> items;

    // Default Constructor
    public OrderHistoryDAO() {
        this.items = new ArrayList<>();
    }

    // Parameterized Constructor
    public OrderHistoryDAO(int orderId, int userId, String restaurantName, Timestamp orderDate, 
                           double totalAmount, String status, String paymentMode) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantName = restaurantName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
        this.items = new ArrayList<>();
    }

    // Inner class to represent individual food items within an order
    public static class OrderItemDetail {
        private String itemName;
        private int quantity;
        private double price;

        public OrderItemDetail() {}

        public OrderItemDetail(String itemName, int quantity, double price) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "OrderItemDetail [itemName=" + itemName + ", quantity=" + quantity + ", price=" + price + "]";
        }
    }

    // Helper method to easily add items to an order
    public void addItem(String itemName, int quantity, double price) {
        this.items.add(new OrderItemDetail(itemName, quantity, price));
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public List<OrderItemDetail> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDetail> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderHistoryDTO [orderId=" + orderId + ", userId=" + userId + ", restaurantName=" + restaurantName
                + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status=" + status
                + ", paymentMode=" + paymentMode + ", items=" + items + "]";
    }
}