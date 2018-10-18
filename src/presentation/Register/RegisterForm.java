package presentation.Register;

import logic.User;
import main.Controller;
import main.Main;
import presentation.components.DroppablePicturePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SYTC307u8365 on 10/5/2017.
 */
public class RegisterForm {
    JPanel rootPanel;
    JTextField emailTextField;
    JButton loginExistingButton;
    JButton registerButton;
    JTextField passwordTextField;
    private JPasswordField passwordConfirmField;
    private DroppablePicturePanel picturePanel;

    public RegisterForm(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        emailTextField.setText(email);
        passwordTextField.setText(password);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User user = User.register(
                        emailTextField.getText(),
                        passwordTextField.getText(),
                        passwordConfirmField.getText(),
                        picturePanel.getImage()
                );
                // some logic to verify that the user is the legitimate user.
                if(user != null) {
                    Controller.setUser(user);
                    System.out.println("Logged in: ID: " + user.getUserID() +
                        ", Email: " + user.getEmail() + ", Role: " + user.getRole());
                    Controller.login();
                }
            }
        });
        loginExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.setUser(new User(-1, emailTextField.getText(), passwordTextField.getText(), "", null));
                Controller.showLogin();
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
