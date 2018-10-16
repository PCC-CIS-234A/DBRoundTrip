package com.company;

import data.Database;
import logic.User;
import presentation.GUI;
import presentation.UsersTableGUI;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static JFrame m_Frame;

    public static void main(String[] args) {
        ArrayList<User> users = Database.fetchUsers();
        GUI gui = new GUI();
        gui.displayUsers(users);
        UsersTableGUI table = new UsersTableGUI();
        m_Frame = new JFrame();
        m_Frame.setContentPane(table.getRootPanel());
        m_Frame.pack();
        m_Frame.setLocationRelativeTo(null);
        m_Frame.setVisible(true);
    }
}
