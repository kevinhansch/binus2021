package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.service.CartService;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Value("${local.server.port}")
    private int serverPort;

    @MockBean
    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = serverPort;
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(cartService);
    }

    @Test
    public void getByCustomerName() {
        List<Cart> expected = Arrays.asList(
                Cart.builder().itemName("Laptop").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Monitor").quantity(1).customerName("Budi").build(),
                Cart.builder().itemName("Keyboard").quantity(1).customerName("Budi").build()
        );

        Mockito.when(cartService.getByCustomerName("Budi")).thenReturn(expected);

        List<Cart> result =
                RestAssured.given()
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                        .when()
                        .get("/carts/Budi")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract().body().jsonPath().getList(".", Cart.class);

        Mockito.verify(cartService).getByCustomerName("Budi");

        Assert.assertEquals(expected.size(), result.size());
        Assert.assertEquals(expected.get(0).getItemName(), result.get(0).getItemName());
        Assert.assertEquals(expected.get(0).getQuantity(), result.get(0).getQuantity());
        Assert.assertEquals(expected.get(0).getCustomerName(), result.get(0).getCustomerName());
    }

    @Test
    public void addToCart() {
        Cart expected = new Cart("TestTambah", 10, "Kevin");

        Mockito.when(cartService.addToCart(expected)).thenReturn(expected);

        Cart result =
                RestAssured.given()
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                        .body("{\"itemName\": \"TestTambah\", \"quantity\": 10, \"customerName\": \"Kevin\"}")
                        .when()
                        .put("/carts")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract().body().jsonPath().getObject(".", Cart.class);

        Mockito.verify(cartService).addToCart(expected);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteByItemNameAndCustomerName() {
        Cart expected = new Cart("Laptop", 1, "Budi");

        Mockito.when(cartService.deleteByItemNameAndCustomerName("Laptop", "Budi")).thenReturn(expected);

        Cart result =
                RestAssured.given()
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                        .when()
                        .delete("/carts/Laptop/Budi")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract().body().jsonPath().getObject(".", Cart.class);

        Mockito.verify(cartService).deleteByItemNameAndCustomerName("Laptop", "Budi");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void updateByItemNameAndCustomerName() {
        Cart expected = new Cart("Laptop", 1000, "Budi");

        Mockito.when(cartService.updateQuantityByItemNameAndCustomerName("Laptop", "Budi", 1000)).thenReturn(expected);

        Cart result =
                RestAssured.given()
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                        .body("{\"itemName\": \"Laptop\", \"quantity\": 1000, \"customerName\": \"Budi\"}")
                        .when()
                        .post("/carts")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .extract().body().jsonPath().getObject(".", Cart.class);

        Mockito.verify(cartService).updateQuantityByItemNameAndCustomerName("Laptop", "Budi", 1000);

        Assert.assertEquals(expected, result);
    }
}