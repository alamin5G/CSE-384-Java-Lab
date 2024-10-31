package order;

import java.util.ArrayList;
import java.util.List;
import uml_diagram.Customer;

/**
 *
 * @author alami
 */
public class Orders extends Customer {

    private static int orderId = 0;
    private String dateCreated;
    private String dateShipped;
    private String customerName;
    private String customerId;
    private String status;
    private String shippingId;
    private List<OrderDetails> orderDetailsList;
    private ShippingInfo shippingInfo;

    public Orders(String dateCreated, String shippingId) {
        orderId++;
        this.dateCreated = dateCreated;
        this.shippingId = shippingId;
        this.status = "Pending"; // Default Pending
        this.orderDetailsList = new ArrayList<>();
    }

    public void placeOrder(ShoppingCart cart, ShippingInfo shippingInfo) {
        //set the shipping info
        this.shippingInfo = shippingInfo;
        // Check out items from cart and add them to the order details
        System.out.println("Placing order with Cart ID: " + ShoppingCart.getCartId());
        for (ShoppingCart.CartItem item : cart.getCartItemList()) {
            OrderDetails orderDetail = new OrderDetails(orderId, item.getProductId(), item.getQuantity(), 10); // Assuming unitCost = 10
            orderDetailsList.add(orderDetail);
        }
        cart.checkOut(); // invoking checkout method
        System.out.println("Order placed successfully with Order ID: " + orderId);
    }

    // display order summary
    public void viewOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Date Created: " + dateCreated);
        System.out.println("Shipping ID: " + shippingId);
        System.out.println("Status: " + status);

        if (shippingInfo != null) {
            System.out.println("Shipping Information:");
            shippingInfo.viewShippingInfo();
        } else {
            System.out.println("No shipping information available.");
        }

        for (OrderDetails detail : orderDetailsList) {
            detail.viewOrderDetail();
        }
    }

    // Method to set shipping information (date shipped and status)
    public void setShippingInfo(String dateShipped, String status) {
        this.dateShipped = dateShipped;
        this.status = status;
    }
}
