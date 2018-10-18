package presentation.ConsoleUI;

import logic.User;

import java.util.ArrayList;

/**
 * A simple presentation interface for writing to the ConsoleUI.
 */
public class Console {

    /**
     * Write a list of users with the given role to the ConsoleUI in CSV format.
     * <p>
     * Doing this the right way requires us to handle " inside of values correctly, and to deal with
     * funny characters like newlines in usernames, but life is too short. That's a "Sprint 3 thing."
     *
     * @param role  The role for users that are to be selected.
     */
    public void displayUsers(String role) {
        ArrayList<User> users = User.fetchUsers(role);
        for (User user: users) {
            System.out.println("\"" + user.getEmail() + "\",\"" + user.getPassword() + "\",\"" + user.getRole() + "\"");
        }
    }
}
