package order;

/**
 *
 * @author alami
 */
public class OrderDetails {
    private int orderId;
    private int productId;
    private int quantity;
    private float unitCost;
    private float subtotal;

    // Constructor
    public OrderDetails(int orderId, int productId, int quantity, float unitCost) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subtotal = calcPrice();
    }

    // Method to calculate total price for the item
    public float calcPrice() {
        return quantity * unitCost;
    }

    // Method to display order details for this item
    public void viewOrderDetail() {
        System.out.println("Product ID: " + productId);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Cost: " + unitCost);
        System.out.println("Subtotal: " + subtotal);
    }
}
