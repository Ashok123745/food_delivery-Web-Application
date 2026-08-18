package com.food.model;

public class Resturant {

	private int resturantId;
	private String name;
	private String cuisineType;
	private int deliveryTime;
	private String address;
	private int adminuserId;
	private double rating;
	private boolean isActive;
	private String imagePath;
	
	
	public Resturant() {
		
	}


	public Resturant( String name, String cuisineType, int deliveryTime, String address,
			int adminuserId, double rating, boolean isActive, String imagePath) {
		super();
		this.name = name;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminuserId = adminuserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
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


	public String getCuisineType() {
		return cuisineType;
	}


	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}


	public int getDeliveryTime() {
		return deliveryTime;
	}


	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getAdminUserId() {
		return adminuserId;
	}


	public void setAdminuserId(int adminUserId) {
		this.adminuserId = adminUserId;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "Resturant [ResturantId=" + resturantId + ", name=" + name + ", cuisineType=" + cuisineType
				+ ", deliveryTime=" + deliveryTime + ", address=" + address + ", adminUserId=" + adminuserId
				+ ", rating=" + rating + ", isActive=" + isActive + ", imagePath=" + imagePath + "]";
	}
	
    
	
	
	
}
