package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register implements ActionListener {
    JFrame frame = new JFrame();

    JLabel emptyLable = new JLabel(); // kostil'

    JButton registerButton = new JButton("Register");
    JButton goBackButton = new JButton("Back");

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField passwordConfirmField = new JPasswordField();

    JLabel registrationLable = new JLabel("Registration");
    JLabel userLable = new JLabel("Username:");
    JLabel passwordLable = new JLabel("Password:");
    JLabel passwordConfirmLable = new JLabel("Confirm password:");

    Register() {
        registrationLable.setBounds(74, 10, 200, 100);
        registrationLable.setFont(new Font(null, Font.ITALIC, 25));
        userLable.setBounds(74, 100, 75, 25);
        passwordLable.setBounds(75, 150, 75, 25);
        passwordConfirmLable.setBounds(28, 200, 125, 25);

        usernameField.setBounds(140, 100, 150, 25);
        passwordField.setBounds(140, 150, 150, 25);
        passwordConfirmField.setBounds(140, 200, 150, 25);

        registerButton.setBounds(75, 250, 75, 25);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);
        goBackButton.setBounds(200, 250, 75, 25);
        goBackButton.addActionListener(this);
        goBackButton.setFocusable(false);

        frame.add(registrationLable);
        frame.add(userLable);
        frame.add(passwordLable);
        frame.add(passwordConfirmLable);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(passwordConfirmField);
        frame.add(registerButton);
        frame.add(goBackButton);
        frame.add(emptyLable);

        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackButton) {
            frame.dispose();
            new Auth();
        }

        if (e.getSource() == registerButton) {
            if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordConfirmField.getPassword()))) {
                frame.dispose();
                new App();
            }

        }
    }
}
