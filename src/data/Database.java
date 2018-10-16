package data;

import logic.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    public static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/DemoGUI";

    public static ArrayList<User> fetchUsers() {
        Connection conn = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING, "DemoGUI", "DemoGUI");
            System.out.println("Connected to the database!!! Getting table list...");
            String query = "SELECT * FROM USERS WHERE Role = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "User");
            rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getString("Email"), rs.getString("Password"), rs.getString("Role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                rs.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
