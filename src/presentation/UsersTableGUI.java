package presentation;

import data.Database;
import logic.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class UsersTableGUI {
    private JTable userTable;
    private JComboBox roleCombo;
    private JPanel rootPanel;

    public UsersTableGUI() {
        /*
        roleCombo.addItem("User");
        roleCombo.addItem("Admin");
        roleCombo.addItem("Therapist");
        */
        ArrayList<User> users = User.fetchUsers();
        for (User user: users) {
            roleCombo.addItem(user.getEmail() + " - " + user.getRole());
        }
        roleCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                System.out.println("Change: " + itemEvent.getItem() + ", " + itemEvent.getStateChange());
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    showTable(itemEvent.getItem().toString());
                }
            }
        });
    }

    private void showTable(String role) {
        TableModel model = userTable.getModel();
        System.out.println(model);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
