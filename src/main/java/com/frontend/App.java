package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.frontend.Main.*;

public class App implements ActionListener {
    JFrame frame = new JFrame();

    JLabel emptyLabel = new JLabel(); // kostil'

    JLabel usernameLabel = new JLabel();
    JLabel idLabel = new JLabel();
    JLabel title = new JLabel();

    JPanel panel = new JPanel(new FlowLayout());

    JButton storageButton = new JButton("Storage");
    JButton shopButton = new JButton("Shop");
    JButton deleteUserButton = new JButton("Delete Account");

    Map<Object, Object> user = new HashMap<>();


    App(Map<Object, Object> response) {
        // frame start
        frame.setTitle("User BIO");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        System.out.println(response.get("id").toString());

        try {
            user = getUserRequest("http://localhost:8080/api/v1/user", response.get("id").toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //label set
        usernameLabel.setText("Username: " + user.get("username").toString());
        title.setText("User BIO");
        title.setFont(new Font(null, Font.ITALIC, 25));
        panel.setBackground(new Color(0x00FFFF));
        deleteUserButton.addActionListener(this);
        shopButton.addActionListener(this);
        storageButton.addActionListener(this);

        //entity location
        title.setBounds(100, 5, 200, 25);
        usernameLabel.setBounds(20, 50, 100, 100);
        idLabel.setBounds(20, 90, 300, 100);
        deleteUserButton.setBounds(20, 180, 150, 25);

        //panel add
        panel.add(shopButton);
        panel.add(storageButton);

        //frame add
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(idLabel);
        frame.add(deleteUserButton);


        // kostil'
        frame.add(emptyLabel);
    }

    App(String id) {
        // frame start
        frame.setTitle("User BIO");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        try {
            user = getUserRequest("http://localhost:8080/api/v1/user", id);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //label set
        usernameLabel.setText("Username: " + user.get("username").toString());
        title.setText("User BIO");
        title.setFont(new Font(null, Font.ITALIC, 25));
        panel.setBackground(new Color(0x00FFFF));
        deleteUserButton.addActionListener(this);
        shopButton.addActionListener(this);
        storageButton.addActionListener(this);

        //entity location
        title.setBounds(100, 5, 200, 25);
        usernameLabel.setBounds(20, 50, 100, 100);
        idLabel.setBounds(20, 90, 300, 100);
        deleteUserButton.setBounds(20, 180, 150, 25);

        //panel add
        panel.add(shopButton);
        panel.add(storageButton);

        //frame add
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(idLabel);
        frame.add(deleteUserButton);

        // kostil'
        frame.add(emptyLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == storageButton) {
            if (user.get("admin").equals(false)) {
                JOptionPane.showMessageDialog(frame, "U're not an admin", "Sever Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            frame.dispose();
            new Storage(user.get("id").toString());
        }

        if (e.getSource() == shopButton) {
            frame.dispose();
            new Shop(user.get("id").toString());
        }

        if (e.getSource() == deleteUserButton) {
            Map<Object, Object> response = new HashMap<>();

            try {
                response = deleteUserRequest("http://localhost:8080/api/v1/user", user.get("id").toString());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (response.get("ErrorMessage") != null) {
                JOptionPane.showMessageDialog(frame, response.get("ErrorMessage").toString(), "Sever Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(frame, "User deleted", "Sever Warning", JOptionPane.WARNING_MESSAGE);
            frame.dispose();
            new Auth();
        }
    }
}
