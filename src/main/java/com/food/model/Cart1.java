package com.food.model;

import java.util.HashMap;
import java.util.Map;

public class Cart1 {
    
    private Map<Integer, Cartitem1> items;
    
    public Cart1() {
        items = new HashMap<Integer, Cartitem1>();
    }

    public Map<Integer, Cartitem1> getItems() {
        return items;
    }

    public void addItem(Cartitem1 cartItem) {
        int menuId = cartItem.getMenuId();
        
        if (items.containsKey(menuId)) {
            Cartitem1 existingCartItem = items.get(menuId);
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity()); 
        } else {
            items.put(menuId, cartItem);
        }
    }

    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity > 0) {
                Cartitem1 existingitem = items.get(itemId);
                existingitem.setQuantity(quantity);
            } else {
                items.remove(itemId);
            }
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public void clear() {
        items.clear();
    }

    public double getTotalprice() {
        double total = 0.0;
        for (Cartitem1 item : items.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }
}