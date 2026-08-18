package com.food.model;

public class Cartitem1 {
    private int menuId;
    private int resturantId;
    private String name;
    private double price;
    private int quantity;
    private String imagePath; // Added imagePath field

    public Cartitem1() {
    }

    public Cartitem1(int menuId, int resturantId, String name, double price, int quantity, String imagePath) {
        this.menuId = menuId;
        this.resturantId = resturantId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getItemId() {
        return menuId;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getTotalPrice() {
        return this.price * this.quantity;
    }
}