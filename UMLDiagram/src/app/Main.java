
package app;

import order.*;
import uml_diagram.Customer;

/**
 *
 * @author alami
 */


public class Main {

    public static void main(String[] args) {
        // Step 1: Register and log in a customer
        Customer customer = new Customer();
        customer.register("customer1", "password123");
       
        customer.setCustomerName("Md Alamin");
        customer.setAddress("123 Tongi");
        customer.setEmail("alamin@alamin.com");
        customer.setCreditCardInfo("1234-5678-9876");
        customer.setShippingInfo("Standard");

        System.out.println("\nLogging in...");
        customer.login("customer", "password123"); //wrong user
        System.out.println("After correct user..");
        customer.login("customer1", "password123"); //correct user

        // Step 2: Create a shopping cart and add items
        ShoppingCart cart = new ShoppingCart();
        System.out.println("\nAdding items to cart...");
        cart.addCartItem(101, 2, 231024); // Product ID: 101, Quantity: 2, Date: 20231030
        cart.addCartItem(102, 1, 231024); // Product ID: 102, Quantity: 1, Date: 20231030
        cart.addCartItem(103, 5, 231024); // Product ID: 103, Quantity: 5, Date: 20231030
        System.out.println("\nView Cart Details: ");
        cart.viewCartDetails();
        
        //update the quantity from cart
        System.out.println("update the quantity for 102 product id");
        cart.updateQuantity(102, 3);
        cart.viewCartDetails();

        // Step 3: Create shipping info
        ShippingInfo shippingInfo = new ShippingInfo(1, "Express", 20, 1001); // Shipping ID: 1, Type: Express, Cost: 20, Region ID: 1001

        // Step 4: Place an order using the cart and shipping info
        Orders order = new Orders("2023-10-30", String.valueOf(shippingInfo.getShippingId())); // Order date: 2023-10-30
        System.out.println("\nPlacing an order...");
        order.placeOrder(cart, shippingInfo); //missing customer information due to the UML diagram restriction

        // Step 5: View order details after placing the order
        System.out.println("\nOrder details:");
        order.viewOrderDetails();
        
        // Step 6: Check if the cart is empty after checkout
        System.out.println("\nViewing cart after checkout:");
        cart.viewCartDetails();

        // Step 7: Update shipping information (e.g., when the item is shipped)
        System.out.println("\nUpdating shipping information...");
        order.setShippingInfo("2023-11-01", "Shipped");
        order.viewOrderDetails();

        // Step 8: Update customer profile
        System.out.println("\nUpdating customer profile...");
        customer.updateProfile();

        
    }
}

