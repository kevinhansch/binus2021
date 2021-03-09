package com.blibli.training.springframework.bean;

import com.blibli.training.springframework.entity.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class BeanConfiguration {

  @Bean(name = "carts")
  public List<Cart> carts() {
    Cart cart1 = Cart.builder().itemName("Laptop").quantity(1).customerName("Budi").build();
    Cart cart2 = Cart.builder().itemName("Monitor").quantity(1).customerName("Budi").build();
    Cart cart3 = Cart.builder().itemName("Keyboard").quantity(1).customerName("Budi").build();
    Cart cart4 = Cart.builder().itemName("Laptop").quantity(1).customerName("Andi").build();
    Cart cart5 = Cart.builder().itemName("Mouse").quantity(1).customerName("Andi").build();
    Cart cart6 = Cart.builder().itemName("Speaker").quantity(1).customerName("Andi").build();
    Cart cart7 = Cart.builder().itemName("Headphone").quantity(1).customerName("Kevin").build();
    Cart cart8 = Cart.builder().itemName("Monitor").quantity(1).customerName("Kevin").build();
    Cart cart9 = Cart.builder().itemName("Keyboard").quantity(1).customerName("Kevin").build();

    return new ArrayList<>(Arrays.asList(cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9));
  }
}
