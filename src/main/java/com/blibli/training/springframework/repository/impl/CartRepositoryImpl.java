package com.blibli.training.springframework.repository.impl;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartRepositoryImpl implements CartRepository {
    @Autowired
    @Qualifier("carts")
    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }
}
