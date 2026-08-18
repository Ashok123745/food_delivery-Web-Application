package com.food.model;

import java.sql.Timestamp;

public class User {
	
	private int userId;
	private String username;
	private String password;
	private String email;
	private String mobile; // Added missing mobile field
	private String address;
	private String role;
	private Timestamp createdDate;
	private Timestamp lastLoginDate;

	// 1. Default Constructor
	public User() {
		
	}

	// 2. Full Parameterized Constructor
	public User(int userId, String username, String password, String email, String mobile, String address, 
			String role, Timestamp createdDate, Timestamp lastLoginDate) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}

	// 3. Constructor without userId & dates (Ideal for Registration in Servlet)
	public User(String username, String password, String email, String mobile, String address, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.role = role;
	}

	// Getters and Setters (With proper 'this' binding)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username; // Fixed: Added 'this.'
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password; // Fixed: Added 'this.' & corrected lowercase casing
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile; // Added getter for mobile
	}

	public void setMobile(String mobile) {
		this.mobile = mobile; // Added setter for mobile
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + ", role=" + role + ", createdDate=" + createdDate
				+ ", lastLoginDate=" + lastLoginDate + "]";
	}
}