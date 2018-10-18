package presentation.UsersTableGUI;

import logic.User;
import main.Controller;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * A form that presents a user interface for browsing users by role. Roles are presented in a combobox
 * and user info is presented in a table. Also includes a button for dumping the current table contents
 * to the ConsoleUI in CSV format.
 */
public class UsersTableGUI {
    private JTable userTable;
    private JComboBox roleCombo;
    private JPanel rootPanel;
    private JButton dumpToConsoleButton;
    private DefaultTableModel m_UserModel;

    /**
     * Constructor for the UsersTableGUI interface. Sets up the role combo and te users table
     * and adds event listeners for the combo box and the "dump to ConsoleUI" button.
     */
    public UsersTableGUI() {
        setupRoleCombo();
        setupUserTableModel();
        showTable(roleCombo.getSelectedItem().toString());
        roleCombo.addItemListener(new ItemListener() {

            // This method gets called twice when the user changes the value in the combobox.
            // The previously selected item gets a state change to deselected and the
            // new selected item gets a state change to selected. For the newly selected
            // item, we want to redisplay the results in the user table.
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                // System.out.println("Change: " + itemEvent.getItem() + ", " + itemEvent.getStateChange());
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    showTable(itemEvent.getItem().toString());
                }
            }
        });
        dumpToConsoleButton.addActionListener(new ActionListener() {

            // This gets called when the user clicks on the dump to ConsoleUI button.
            // It tells the controller to show users in the ConsoleUI.
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.showUsersInConsole(roleCombo.getSelectedItem().toString());
            }
        });
    }

    /**
     * Fetches all the roles used and displays them in the role combobox.
     */
    private void setupRoleCombo() {
        ArrayList<String> roles = User.fetchRoles();
        for(String role: roles) {
            roleCombo.addItem(role);
        }
    }

    /**
     * Display a list of users in the users table. Selects the users with the given role.
     *
     * @param role  The role for users to be selected (User/Therapist/Admin)
     */
    private void showTable(String role) {
        // Ask the User class to fetch the users with the given role
        ArrayList<User> users = User.fetchUsers(role);
        // clear the previous data out of the table
        m_UserModel.setRowCount(0);
        // Add each user to the table
        for (User user: users) {
            m_UserModel.addRow(new Object[] {
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
            });
        }
    }

    /**
     * Initialize the users table in the form. Defines the columns for the table and sets some
     * properties for the table model.
     */
    private void setupUserTableModel() {
        // Create a default table model with three columns named Email, Password and Role, and no table data.
        m_UserModel = new DefaultTableModel(
                // Initial data (empty)
                new Object[][]{},
                // Initial columns
                new Object[] { "Email ", "Password", "Role" }
        ) {
            // Do not let the user edit values in the table.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Make the userTable use that model
        userTable.setModel(m_UserModel);

        // Center values in the Password and Role columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        userTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        userTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
    }

    /**
     * Returns the root JPanel for the UsersTableGUI form.
     *
     * @return  The root JPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
