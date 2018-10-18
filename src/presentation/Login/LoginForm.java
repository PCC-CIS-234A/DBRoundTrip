package presentation.Login;

import logic.User;
import main.Controller;
import presentation.GUIForm;

import javax.swing.*;

/**
 * Created by SYTC307u8365 on 10/5/2017.
 */
public class LoginForm extends GUIForm {
    JPanel rootPanel;
    JTextField emailTextField;
    JButton newAccountButton;
    JButton loginButton;
    JTextField passwordTextField;

    public LoginForm(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        emailTextField.setText(email);
        passwordTextField.setText(password);

        loginButton.addActionListener(actionEvent -> {
            User.login(emailTextField.getText(), passwordTextField.getText());
        });

        newAccountButton.addActionListener(actionEvent -> {
            Controller.setUser(new User(-1, emailTextField.getText(), passwordTextField.getText(), "", null));
            Controller.showRegister();
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
