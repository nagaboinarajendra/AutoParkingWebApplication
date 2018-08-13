package org.epam.admin;
import org.epam.exceptions.InvalidAdminException;
/**
 * Admin class to check if admin is valid or not.
 * @author rajendra
 */
public class Admin {
    /**
     * username of admin.
     */
    private String adminUserName = "admin";
    /**
     * password of admin.
     */
    private String adminPassword = "admin";
    /**
     * @param username entered by admin.
     * @param password entered by admin
     * @return true if admin valid else false
     * @throws InvalidAdminException when admin is invalid
     */
    public boolean validateAdmin(String username, String password)
            throws InvalidAdminException {
        boolean isAdminValid = false;
        if (username.equals(adminUserName) && password.equals(adminPassword)) {
            isAdminValid = true;
        } else {
            throw new InvalidAdminException("admin credentials are incorrect");
        }
        return isAdminValid;
    }
}
