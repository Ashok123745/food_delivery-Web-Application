package com.food.model;

public class Cart {
	private int CartId;
	private int UserId;
	
	
	public Cart() {
		
	}


	public Cart(int CartId, int UserId) {
		super();
		this.CartId = CartId;
		this.UserId = UserId;
	}


	public int getCartId() {
		return CartId;
	}


	public void setCartId(int cartId) {
		this.CartId = cartId;
	}


	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		this.UserId = userId;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + CartId + ", userId=" + UserId + "]";
	}
	
	
	

}
