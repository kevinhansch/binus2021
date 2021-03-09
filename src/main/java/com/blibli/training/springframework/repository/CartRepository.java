package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> getCarts();
}
