package data;

import logic.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Database class for the data layer. Note that there is only one database, so all the properties
 * and methods are static, and the single connection object is shared across all calls to the
 * database methods.
 */
public class Database {
    // Connection string for connecting to SQL Server at CISDBSS, using the DemoGUI database.
    // Requires jtds.XXX.jar to be included in the project with the correct dependency set.
    public static final String CONNECTION_STRING = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/DemoGUI";

    // Some SQL queries.
    private static final String FETCH_USERS_QUERY = "SELECT Email, Password, Role FROM USERS WHERE Role = ?;";
    private static final String FETCH_ROLES_QUERY = "SELECT DISTINCT Role FROM USERS ORDER BY Role;";

    // The one and only connection object
    private static Connection m_Connection = null;

    /**
     * Create a new connection object if there isn't one already.
     */
    private static void connect() {
        if (m_Connection != null)
            return;
        try {
            // Create a database connection with the given username and password.
            m_Connection = DriverManager.getConnection(CONNECTION_STRING, "DemoGUI", "DemoGUI");
        } catch (SQLException e) {
            System.err.println("Error! Couldn't connect to the database!");
        }
    }

    /**
     * Fetch a list of users with the given role.
     *
     * @param role  The role for users that we want to fetch
     * @return      The list of users matching that role.
     */
    public static ArrayList<User> fetchUsers(String role) {
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            // Create a connection if there isn't one already
            connect();

            // Prepare a SQL statement
            PreparedStatement stmt = m_Connection.prepareStatement(FETCH_USERS_QUERY);

            // This one has a single parameter for the role, so we bind the value of role to the parameter
            stmt.setString(1, role);

            // Execute the query returning a result set
            rs = stmt.executeQuery();

            // For each row in the result set, create a new User object with the specified values
            // and add it to the list of results.
            while (rs.next()) {
                users.add(new User(rs.getString("Email"), rs.getString("Password"), rs.getString("Role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the list of results. Will be an empty list if there was an error.
        return users;
    }

    /**
     * Return all the roles that are in use in the USERS table.
     *
     * @return  The list of roles, as Strings.
     */
    public static ArrayList<String> fetchRoles() {
        ResultSet rs = null;
        ArrayList<String> roles = new ArrayList<>();

        try {
            // Create a connection if there isn't one already
            connect();

            // Prepare the SQL statement
            PreparedStatement stmt = m_Connection.prepareStatement(FETCH_ROLES_QUERY);

            // This statement doesn't currently have any parameters, so we don't need to bind anything.
            // Execute the query returning a result set.
            rs = stmt.executeQuery();

            // For each result in the result set, add the Role value to the list of results.
            while (rs.next()) {
                roles.add(rs.getString("Role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the list of results. Will return an empty list if there was an error.
        return roles;
    }


    public static User lookupUser(String email) {
        connect();
        String query = "SELECT UserID, Email, Password, Role, Image FROM USERS WHERE Email = ?";
        try {
            PreparedStatement stmt = m_Connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                InputStream stream = rs.getBinaryStream("Image");
                BufferedImage image = null;

                try {
                    if (stream != null)
                        image = ImageIO.read(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return new User(rs.getInt("UserID"), rs.getString("Email"), rs.getString("Password"), rs.getString("Role"), image);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User registerUser(String email, String password, BufferedImage image) {
        connect();
        String query = "INSERT INTO USERS VALUES (?, ?, '" + User.USER_ROLE + "', ?); SELECT SCOPE_IDENTITY() AS ID;";
        try {
            PreparedStatement stmt = m_Connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = null;
            int length = 0;

            try {
                if (image != null) {
                    ImageIO.write(image, "png", outputStream);
                    inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                    length = inputStream.available();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            stmt.setBinaryStream(3, inputStream, length);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                return new User(rs.getInt("ID"), email, password, User.USER_ROLE, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
