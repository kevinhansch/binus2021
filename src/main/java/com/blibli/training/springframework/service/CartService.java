package com.blibli.training.springframework.service;

import com.blibli.training.springframework.entity.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getByCustomerName(String customerName);

    Cart addToCart(Cart cart);

    Cart deleteByItemNameAndCustomerName(String itemName, String customerName);

    Cart updateQuantityByItemNameAndCustomerName(String itemName, String customerName, int quantity);
}
