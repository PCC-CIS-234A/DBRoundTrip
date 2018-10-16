package presentation;

import logic.User;

import java.util.ArrayList;

public class GUI {
    public void displayUsers(ArrayList<User> users) {
        for (User user: users) {
            System.out.println(user.getEmail() + ", " + user.getRole());
        }
    }
}
