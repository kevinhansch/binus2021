package com.blibli.training.springframework.service.impl;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.repository.CartRepository;
import com.blibli.training.springframework.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getByCustomerName(String customerName) {
        List<Cart> cartsByCustomerName = new ArrayList<>();
        for (Cart cart : cartRepository.getCarts()) {
            if (cart.getCustomerName().equals(customerName)) {
                cartsByCustomerName.add(cart);
            }
        }
        return cartsByCustomerName;
    }

    @Override
    public Cart addToCart(Cart cart) {
        cartRepository.getCarts().add(cart);
        return cart;
    }

    @Override
    public Cart deleteByItemNameAndCustomerName(String itemName, String customerName) {
        Cart removedCart = null;

        for (Cart cart : cartRepository.getCarts()) {
            if (cart.getItemName().equals(itemName) && cart.getCustomerName().equals(customerName)) {
                cartRepository.getCarts().remove(cart);
                removedCart = cart;
                break;
            }
        }

        return removedCart;
    }

    @Override
    public Cart updateQuantityByItemNameAndCustomerName(String itemName, String customerName, int quantity) {
        Cart updatedCart = null;

        for (Cart cart : cartRepository.getCarts()) {
            if (cart.getItemName().equals(itemName) && cart.getCustomerName().equals(customerName)) {
                cart.setQuantity(quantity);
                updatedCart = cart;
                break;
            }
        }

        return updatedCart;
    }
}
