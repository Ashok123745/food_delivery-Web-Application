package com.food.DAO;

import java.util.List;

import com.food.model.CartItem;

public interface CartItemDAO {

    void addCartItem(CartItem cartItem);

    CartItem getCartItem(int cartItemId);

    void updateCartItem(CartItem cartItem);

    void deleteCartItem(int cartItemId);

    List<CartItem> getAllCartItems();
}