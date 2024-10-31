
package uml_diagram;

/**
 *
 * @author alami
 */
public class User {
    private String userId;
    private String password;
    private String loginStatus;

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public boolean verifyLogin(String userId, String password){
        if(loginStatus.equals("activate")){
            if(this.userId.equals(userId) && this.password.equals(password)){
                return true;
            }
        }
        return false;
    }
}
