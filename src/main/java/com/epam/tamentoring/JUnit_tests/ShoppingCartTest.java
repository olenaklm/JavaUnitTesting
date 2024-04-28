package com.epam.tamentoring.JUnit_tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        List<Product> products = new ArrayList<>();
        shoppingCart = new ShoppingCart(products);
    }

    @Test
    public void testAddProductToCart() {
        Product product = new Product(1, "Laptop", 1000, 1);
        shoppingCart.addProductToCart(product);
        assertEquals(1, shoppingCart.getProducts().size());
    }

    @Test
    public void testRemoveProductFromCart() {
        Product product = new Product(1, "Laptop", 1000, 1);
        shoppingCart.addProductToCart(product);
        shoppingCart.removeProductFromCart(product);
        assertEquals(0, shoppingCart.getProducts().size());
    }

    @Test
    public void testGetTotalPriceOfCart() {
        Product product1 = new Product(1, "Laptop", 1000, 1);
        Product product2 = new Product(2, "Mouse", 20, 3);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        assertEquals(1060, shoppingCart.getCartTotalPrice());
    }

    @Test
    public void testRemoveNonExistingProductFromCart() {
        Product product = new Product(1, "Laptop", 1000, 1);
        assertThrows(ProductNotFoundException.class, () -> shoppingCart.removeProductFromCart(product));
    }
}
