package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UsersTableGUI {
    private JTable userTable;
    private JComboBox roleCombo;
    private JPanel rootPanel;

    public UsersTableGUI() {
        roleCombo.addItem("User");
        roleCombo.addItem("Admin");
        roleCombo.addItem("Therapist");
        roleCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                System.out.println("Change: " + itemEvent.getItem() + ", " + itemEvent.getStateChange());
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    // show table
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
