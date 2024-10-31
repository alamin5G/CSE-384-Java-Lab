package uml_diagram;

/**
 *
 * @author alami
 */
public class Administrator extends User {

    private String adminName;
    private String email;

   public Administrator(String adminName, String email, String userId, String password) {
        this.adminName = adminName;
        this.email = email;
        super.setUserId(userId);
        super.setPassword(password);
        super.setLoginStatus("activate");
    }

    public boolean updateCatalog(String adminName) {
        return this.adminName.equals(adminName);
    }

}
