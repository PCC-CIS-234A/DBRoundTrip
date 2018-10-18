package logic;

import data.Database;
import main.Controller;
import main.Main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Business logic class for holding user data and functionality.
 */
public class User {
    private int m_UserID;
    private String m_Email;
    private String m_Password;
    private String m_Role;
    private BufferedImage m_Image;

    public static final String ADMIN_ROLE = "Admin";
    public static final String THERAPIST_ROLE = "Therapist";
    public static final String USER_ROLE = "User";

    /**
     * static method for fetching the list of users with a given role.
     *
     * @param role  The role for users that we wish to fetch
     * @return      The list of users matching the role
     */
    public static ArrayList<User> fetchUsers(String role) {
        return Database.fetchUsers(role);
    }

    /**
     * Static method that returns the list of roles in use in the USERS table of the database.
     *
     * @return  The list of roles.
     */
    public static ArrayList<String> fetchRoles() {
        return Database.fetchRoles();
    }

    /**
     * Construct a new User object with all values specified.
     *
     * @param userID
     * @param email
     * @param password
     * @param role
     * @param image
     */
    public User(int userID, String email, String password, String role, BufferedImage image) {
        m_UserID = userID;
        m_Email = email;
        m_Password = password;
        m_Role = role;
        m_Image = image;
    }

    /**
     * Construct a User object with the given email, password, and role.
     *
     * @param email
     * @param password
     * @param role
     */
    public User(String email, String password, String role) {
        m_Email = email;
        m_Role = role;
        m_Password = password;
    }

    public static void login(String em, String pwd) {

        // some logic to verify that the user is the legitimate user.
        User user = Database.lookupUser(em);

        if(user == null) {
            System.out.println("User not found, need an error dialog.");
            return;
        }
        System.out.println("ID: " + user.getUserID() + ", Email: " + user.getEmail() + ", Password: " + user.getPassword() + ", Role: " + user.getRole());
        if(user.getPassword().equals(pwd)) {
            System.out.println("User " + user.getUserID() + " logged in successfully.");
            Controller.setUser(user);
            Controller.login();
        } else {
            System.out.println("Password incorrect, need an error dialog.");
        }
    }

    public static User register(String email, String password, String passwordConfirm, BufferedImage image) {
        if(!password.equals(passwordConfirm)) {
            System.out.println("Password not equal confirm password. Alert dialog goes here.");
            return null;
        }
        /// validate password and email
        User user = Database.lookupUser(email);

        if(user != null) {
            System.out.println("User already exists! Alert goes here.");
            return null;
        }

        user = Database.registerUser(email, password, image);
        return user;
    }

    // note that only getters are provided, User objects are essentially immutable because
    // the properties are all private and no setters are provided.

    public int getUserID() {
        return m_UserID;
    }

    public String getEmail() {
        return m_Email;
    }

    public String getPassword() {
        return m_Password;
    }

    public String getRole() {
        return m_Role;
    }

    public BufferedImage getImage() {
        return m_Image;
    }
}
