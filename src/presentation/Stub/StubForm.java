package presentation.Stub;

import logic.User;
import main.Controller;
import main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SYTC307u8365 on 10/10/2017.
 */
public class StubForm {
    private JButton startOverButton;
    private JPanel rootPanel;
    private presentation.components.PicturePanel picturePanel;


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public StubForm(User user) {
        picturePanel.setImage(user.getImage());

        startOverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.setUser(new User(-1, "", "", "", null));
                Controller.showLogin();
            }
        });
    }
}
