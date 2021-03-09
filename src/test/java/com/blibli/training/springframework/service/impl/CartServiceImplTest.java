package com.blibli.training.springframework.service.impl;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.repository.CartRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(cartRepository);
    }

    @Test
    public void getByCustomerName() {
        List<Cart> expected = Arrays.asList(
                Cart.builder().itemName("Laptop").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Monitor").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Keyboard").quantity(1).customerName("Budi").build()
        );

        Mockito.when(cartRepository.getCarts()).thenReturn(expected);

        List<Cart> result = cartService.getByCustomerName("Budi");

        Assert.assertEquals(expected.size(), result.size());
        Assert.assertEquals(expected, result);

        Mockito.verify(cartRepository).getCarts();
    }

    @Test
    public void addToCart() {
        List<Cart> carts = new LinkedList<>(Arrays.asList(
                Cart.builder().itemName("Laptop").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Monitor").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Keyboard").quantity(1).customerName("Budi").build()
        ));

        Cart expected = new Cart("TestTambah", 10, "Kevin");

        Mockito.when(cartRepository.getCarts()).thenReturn(carts);

        Cart result = cartService.addToCart(expected);

        Assert.assertEquals(expected.getItemName(), result.getItemName());
        Assert.assertEquals(expected.getQuantity(), result.getQuantity());
        Assert.assertEquals(expected.getCustomerName(), result.getCustomerName());

        Mockito.verify(cartRepository).getCarts();
    }

    @Test
    public void deleteByItemNameAndCustomerName() {
        List<Cart> carts = new LinkedList<>(Arrays.asList(
                Cart.builder().itemName("Laptop").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Monitor").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Keyboard").quantity(1).customerName("Budi").build()
        ));

        Cart expected = new Cart("Laptop", 1, "Budi");

        Mockito.when(cartRepository.getCarts()).thenReturn(carts);

        Cart result = cartService.deleteByItemNameAndCustomerName("Laptop", "Budi");

        Assert.assertEquals(expected, result);

        Mockito.verify(cartRepository, Mockito.times(2)).getCarts();
    }

    @Test
    public void updateByItemNameAndCustomerName() {
        List<Cart> carts = new LinkedList<>(Arrays.asList(
                Cart.builder().itemName("Laptop").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Monitor").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Keyboard").quantity(1).customerName("Budi").build()
        ));

        Cart expected = new Cart("Laptop", 1000, "Budi");

        Mockito.when(cartRepository.getCarts()).thenReturn(carts);

        Cart result = cartService.updateQuantityByItemNameAndCustomerName("Laptop", "Budi", 1000);

        Assert.assertEquals(expected, result);

        Mockito.verify(cartRepository).getCarts();
    }
}