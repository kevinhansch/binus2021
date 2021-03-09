package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(path = "/{customerName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Cart> getByCustomerName(@PathVariable String customerName) {
        return cartService.getByCustomerName(customerName);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @RequestMapping(path = "/{itemName}/{customerName}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Cart deleteCartByItemNameAndCustomerName(@PathVariable String itemName, @PathVariable String customerName) {
        return cartService.deleteByItemNameAndCustomerName(itemName, customerName);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Cart updateCartByItemNameAndCustomerName(@RequestBody Cart cart) {
        return cartService.updateQuantityByItemNameAndCustomerName(cart.getItemName(), cart.getCustomerName(), cart.getQuantity());
    }
}
