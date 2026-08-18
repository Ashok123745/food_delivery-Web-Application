package com.food.model;

public class Menu {
    
    private int Menuid;
    private int resturantid;
    private String itemName;
    private String description;
    private double price;
    private boolean isAvaiable;
    private double rating;
    private String imagepath;
    private String category;

    public Menu() {
        
    }

    public Menu(int resturantid, String itemName, String description, double price, boolean isAvaiable,
            double rating, String imagepath, String category) {
        super();
        this.resturantid = resturantid;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.isAvaiable = isAvaiable;
        this.rating = rating;
        this.imagepath = imagepath;
        this.category = category;
    }

    public int getMenuid() {
        return Menuid;
    }

    public void setMenuid(int menuid) {
        Menuid = menuid;
    }

    public int getResturantid() {
        return resturantid;
    }

    public void setResturantid(int resturantid) {
        this.resturantid = resturantid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvaiable() {
        return isAvaiable;
    }

    public void setAvaiable(boolean isAvaiable) {
        this.isAvaiable = isAvaiable;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Menu [Menuid=" + Menuid + ", resturantid=" + resturantid + ", itemName=" + itemName + ", description="
                + description + ", price=" + price + ", isAvaiable=" + isAvaiable + ", rating=" + rating
                + ", imagepath=" + imagepath + ", category=" + category + "]";
    }
}