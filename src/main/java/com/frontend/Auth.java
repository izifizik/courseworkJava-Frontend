package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Auth extends WindowAdapter implements ActionListener {

    JFrame frame = new JFrame();

    JLabel emptyLable = new JLabel(); // kostil'

    JButton signInButton = new JButton("Sign in");
    JButton signUpButton = new JButton("Sign up");

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    JLabel loginLable = new JLabel("Login");
    JLabel userLable = new JLabel("Username:");
    JLabel passwordLable = new JLabel("Password:");

    Auth() {
        loginLable.setBounds(74, 10, 100, 100);
        loginLable.setFont(new Font(null, Font.ITALIC, 25));
        userLable.setBounds(75, 100, 75, 25);
        passwordLable.setBounds(75, 150, 75, 25);

        usernameField.setBounds(140, 100, 125, 25);
        passwordField.setBounds(140, 150, 125, 25);

        signInButton.setBounds(75, 200, 100, 25);
        signInButton.addActionListener(this);
        signInButton.setFocusable(false);
        signUpButton.setBounds(200, 200, 100, 25);
        signUpButton.addActionListener(this);
        signUpButton.setFocusable(false);

        frame.add(loginLable);
        frame.add(userLable);
        frame.add(passwordLable);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(signInButton);
        frame.add(signUpButton);
        frame.add(emptyLable);

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            frame.dispose();
            new Register();
        }
    }
}
