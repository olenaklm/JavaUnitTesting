package com.epam.tamentoring.Mockito_tests;

import com.epam.tamentoring.bo.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testGetOrderPriceWithDiscountForJohnSmith() {
        // Create a ShoppingCart instance with some dummy products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product", 10.0, 1));
        ShoppingCart shoppingCart = new ShoppingCart(products);

        // Create a UserAccount instance for John Smith with the specified date of birth and shopping cart
        UserAccount johnSmith = new UserAccount("John", "Smith", "1990/10/10", shoppingCart);

        // Mock the behavior of calculateDiscount method to return 3.0
        Mockito.when(discountUtility.calculateDiscount(johnSmith)).thenReturn(3.0);

        // Set the expected total price after applying the discount
        double expectedTotalPrice = shoppingCart.getCartTotalPrice() - 3.0;

        // Call the getOrderPrice method
        double totalPrice = orderService.getOrderPrice(johnSmith);

        // Assert that the total price after discount is correct
        assertEquals(expectedTotalPrice, totalPrice);

        // Verify that calculateDiscount method is called only once with the provided user
        Mockito.verify(discountUtility, Mockito.times(1)).calculateDiscount(johnSmith);

        // Verify that there are no other interactions with the mocked object
        Mockito.verifyNoMoreInteractions(discountUtility);
    }
}
