package com.fda.model;

import java.util.*;

public class Cart {
    private Map<Integer, CartItem> items = new HashMap<>();
    private Integer restaurantId; // Track which restaurant the cart belongs to

    // Add an item to the cart
    public void addCartItem(CartItem newItem, Integer newRestaurantId) {
        // If cart is empty, set restaurantId
        if (this.restaurantId == null) {
            this.restaurantId = newRestaurantId;
        }

        // If the restaurant is different, clear the cart
        if (!this.restaurantId.equals(newRestaurantId)) {
            items.clear();
            this.restaurantId = newRestaurantId;
        }

        // Add or update quantity
        CartItem existing = items.get(newItem.getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + newItem.getQuantity());
        } else {
            items.put(newItem.getId(), newItem);
        }
    }

    // Update quantity for existing item
    public void updateCartItem(CartItem item, int change) {
        CartItem existing = items.get(item.getId());
        if (existing != null) {
            int newQty = existing.getQuantity() + change;
            if (newQty <= 0) {
                items.remove(item.getId());
            } else {
                existing.setQuantity(newQty);
            }
            // If cart becomes empty, reset restaurantId
            if (items.isEmpty()) {
                restaurantId = null;
            }
        }
    }

    // Remove an item from the cart
    public void removeCartItem(CartItem item) {
        items.remove(item.getId());
        if (items.isEmpty()) {
            restaurantId = null;
        }
    }

    // Get all items
    public Collection<CartItem> getItems() {
        return items.values();
    }

    // Get total price
    public double getTotalPrice() {
        return items.values().stream()
            .mapToDouble(i -> i.getPrice() * i.getQuantity())
            .sum();
    }

    // Get the restaurant ID of the current cart
    public Integer getRestaurantId() {
        return restaurantId;
    }
}
