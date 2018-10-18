package main;

import logic.User;
import presentation.ConsoleUI.Console;
import presentation.UsersTableGUI.UsersTableGUI;
import presentation.Login.LoginForm;
import presentation.Register.RegisterForm;
import presentation.Stub.StubForm;

import javax.swing.*;
import java.security.InvalidParameterException;

/**
 * An application controller that's responsible for coordinating/communicating between different
 * applications. All inter-app communication should go through the controller.
 * <p>
 * There's only one application, so we only need one controller. That's why everything here is static.
 * <p>
 * Manages an instance of a JFrame for the currently running GUI as well as an instance of the Console
 * class for writing to the ConsoleUI.
 */
public class Controller {
    private static JFrame m_Frame;
    private static Console m_Console;
    private static User m_User = new User(-1, "", "", "", null);

    /**
     * Constructor for Controller objects. Creates an instance of the Console class for writing users to
     * the ConsoleUI, and starts up and shows the users table GUI.
     */
    
    public static void start() {
        m_Console = new Console();
        createGUI();
    }

    public static void createGUI() {
        // Create a JFrame to show our form in, and display the UsersTableGUI form.
        m_Frame = new JFrame();
        // Makes the application close when the window goes away.
        m_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showForm(LoginForm.class);
    }

    public static void showForm(Class<?> form) throws InvalidParameterException {
        JPanel root = null;
        
        if (form == LoginForm.class)
            root = new LoginForm(m_User).getRootPanel();
        else if (form == RegisterForm.class)
            root = new RegisterForm(m_User).getRootPanel();
        else if (form == StubForm.class)
            root = new StubForm(m_User).getRootPanel();
        else if (form == UsersTableGUI.class)
            root = new UsersTableGUI().getRootPanel();
        else
            throw(new InvalidParameterException("Unknown form type in controller " + form.toString()));
        m_Frame.getContentPane().removeAll();
        m_Frame.getContentPane().add(root);
        m_Frame.pack();
        m_Frame.setLocationRelativeTo(null);
        m_Frame.setVisible(true);
    }

    public static void showLogin() {
        showForm(LoginForm.class);
    }

    public static void showRegister() {
        showForm(RegisterForm.class);
    }

    /**
     * Tell the ConsoleUI to display the list of users with a given role.
     *
     * @param role  The role of users that we wish to display.
     */
    public static void showUsersInConsole(String role) {
        m_Console.displayUsers(role);
    }
    
    public static void setUser(User user) {
        m_User = user;
    }

    public static void login() {
        if (m_User.getRole().equals(User.ADMIN_ROLE))
            showForm(UsersTableGUI.class);
        else
            showForm(StubForm.class);
    }
}
