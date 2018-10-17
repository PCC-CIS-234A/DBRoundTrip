package logic;

import data.Database;

import java.util.ArrayList;

public class User {
    private String m_Email;
    private String m_Role;
    private String m_Password;

    public static ArrayList<User> fetchUsers() {
        return Database.fetchUsers();
    }

    public User(String email, String password, String role) {
        m_Email = email;
        m_Role = role;
        m_Password = password;
    }

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
