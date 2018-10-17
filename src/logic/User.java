package logic;

import data.Database;

import java.util.ArrayList;

/**
 * Business logic class for holding user data and functionality.
 */
public class User {
    private String m_Email;
    private String m_Role;
    private String m_Password;

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

    // note that only getters are provided, User objects are essentially immutable because
    // the properties are all private and no setters are provided.
    public String getEmail() {
        return m_Email;
    }

    public String getRole() {
        return m_Role;
    }

    public String getPassword() {
        return m_Password;
    }
}
