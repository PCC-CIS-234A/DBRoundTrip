package main;

import presentation.Console;
import presentation.UsersTableGUI;

import javax.swing.*;

/**
 * An application controller that's responsible for coordinating/communicating between different
 * applications. All inter-app communication should go through the controller.
 * <p>
 * There's only one application, so we only need one controller. That's why everything here is static.
 * <p>
 * Manages an instance of a JFrame for the currently running GUI as well as an instance of the Console
 * class for writing to the console.
 */
public class Controller {
    static JFrame m_Frame;
    static Console m_Console;

    /**
     * Constructor for Controller objects. Creates an instance of the Console class for writing users to
     * the console, and starts up and shows the users table GUI.
     */
    public static void start() {
        m_Console = new Console();
        showUsersTable();
    }

    /**
     * Start the users table GUI.
     */
    private static void showUsersTable() {
        // Pass this controller object into the UsersTableGUI so that the GUI can tell the controller
        // when important stuff happens.
        UsersTableGUI table = new UsersTableGUI();

        // Create a JFrame to show our form in, and display the UsersTableGUI form.
        m_Frame = new JFrame();
        // Makes the application close when the window goes away.
        m_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Shows the GUI in the JFrame
        m_Frame.setContentPane(table.getRootPanel());
        // Resizes the JFrame to fit the GUI
        m_Frame.pack();
        // Centers the JFrame on the screen
        m_Frame.setLocationRelativeTo(null);
        // Actually shows the JFrame
        m_Frame.setVisible(true);
    }

    /**
     * Tell the console to display the list of users with a given role.
     *
     * @param role  The role of users that we wish to display.
     */
    public static void showUsersInConsole(String role) {
        m_Console.displayUsers(role);
    }
}
