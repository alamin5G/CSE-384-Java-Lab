package uml_diagram;

import java.util.Scanner;

/**
 *
 * @author alami
 */
public class Customer extends User {

    private String customerName;
    private String address;
    private String email;
    private String creditCardInfo;
    private String shippingInfo;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(String shippingInfo) {
        this.shippingInfo = shippingInfo;
    }
    
    //register

    public void register(String userId, String password) {
        super.setUserId(userId);
        super.setPassword(password);
        super.setLoginStatus("activate");
    }
    
    //login
    public void login(String userId, String password){
        boolean success = verifyLogin(userId, password);
        
        if(!success){
            System.out.println("Login Failed");
        }else{
            System.out.println("Logn Success");
        }
    }
    
    public void updateProfile(String customerName, String address, String email, String creditCardInfo, String shippingInfo){
        System.out.println("Which information you want to update? "
                + "\n1. For Your Name"
                + "\n2. For Address"
                + "\n3. For Email"
                + "\n4. For Credit Card"
                + "\n5. For Shipping Info");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        boolean flag = false;
        switch(choice){
            
            case 1:  this.setCustomerName(customerName); flag = true; break;
            case 2:  this.setAddress(address); flag = true; break;
            case 3:  this.setEmail(email); flag = true; break;
            case 4:  this.setCreditCardInfo(creditCardInfo); flag = true; break;
            case 5:  this.setShippingInfo(shippingInfo); flag = true; break;
            default:
                System.out.println("You weren't entering the right choice...");
        }
        
        if(flag){
            System.out.println("Profile updated succcesfully!");
        }
       
    }

}
