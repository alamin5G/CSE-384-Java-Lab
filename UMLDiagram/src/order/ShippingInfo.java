package order;

/**
 *
 * @author alami
 */
public class ShippingInfo {
    private int shippingId;
    private String shippingType;
    private int shippingCost;
    private int shippingRegionId;

    // Constructor
    public ShippingInfo(int shippingId, String shippingType, int shippingCost, int shippingRegionId) {
        this.shippingId = shippingId;
        this.shippingType = shippingType;
        this.shippingCost = shippingCost;
        this.shippingRegionId = shippingRegionId;
    }

    // Method to update shipping information
    public void updateShippingInfo(String shippingType, int shippingCost, int shippingRegionId) {
        this.shippingType = shippingType;
        this.shippingCost = shippingCost;
        this.shippingRegionId = shippingRegionId;
        System.out.println("Shipping information updated successfully.");
    }

   public void viewShippingInfo() {
        System.out.println("Shipping ID: " + shippingId);
        System.out.println("Shipping Type: " + shippingType);
        System.out.println("Shipping Cost: " + shippingCost);
        System.out.println("Shipping Region ID: " + shippingRegionId);
    }
   
}
