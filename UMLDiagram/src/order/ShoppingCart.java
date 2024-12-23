package order;

import java.util.ArrayList;
import java.util.List;
import uml_diagram.Customer;

/**
 *
 * @author alami
 */
public class ShoppingCart extends Customer {

    private static int cartId = 0;
    private int productId;
    private int quantity;
    private int dateAdded;

    private List<CartItem> cartItemList;

    public ShoppingCart() {
        cartId++;
        cartItemList = new ArrayList<>();
    }

    public void addCartItem(int productId, int quantity, int dateAdded) {
        cartItemList.add(new CartItem(productId, quantity, dateAdded));
        System.out.println("Item added to cart: Product ID - " + productId + ", Quantity - " + quantity + " , Date: " + dateAdded);
    }

    public void updateQuantity(int productId, int newQuantiy) {
        boolean b = false;
        for (CartItem item : cartItemList) {
            if (item.productId == productId) {
                item.quantity = newQuantiy;
                b = true;
            }
        }
        if(!b){
            System.out.println("Product ID not found");
        }
    }

    public void viewCartDetails() {
        System.out.println("Cart ID: " + cartId);
        for (CartItem item : cartItemList) {
            System.out.println("Product ID: " + item.productId + ", Quantity: " + item.quantity + ", Date Added: " + item.dateAdded);
        }
        System.out.println("");
    }

    public void checkOut() {
        System.out.println("Checking out the added items:");
        for (CartItem item : cartItemList) {
            System.out.println("Product ID: " + item.productId + ", Quantity: " + item.quantity);
        }
        cartItemList.clear();
        System.out.println("Checkout complete... Your cart is now empty");
   
    }

    //inner class for Item
    class CartItem {

        private int productId;
        private int quantity;
        private int dateAdded;

        public CartItem(int productId, int quantity, int dateAdded) {
            this.productId = productId;
            this.quantity = quantity;
            this.dateAdded = dateAdded;
       }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(int dateAdded) {
            this.dateAdded = dateAdded;
        }
        
        
    }

    //getter and setter
    public static int getCartId() {
        return cartId;
    }

    public static void setCartId(int cartId) {
        ShoppingCart.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(int dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    
}
