package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.frontend.Main.postRequest;


public class Register implements ActionListener {
    JFrame frame = new JFrame();

    JLabel emptyLabel = new JLabel(); // kostil'

    JButton registerButton = new JButton("Register");
    JButton goBackButton = new JButton("Back");

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField passwordConfirmField = new JPasswordField();

    JLabel registrationLabel = new JLabel("Registration");
    JLabel userLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel passwordConfirmLabel = new JLabel("Confirm password:");

    Register() {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        registrationLabel.setBounds(74, 10, 200, 100);
        registrationLabel.setFont(new Font(null, Font.ITALIC, 25));
        userLabel.setBounds(74, 100, 75, 25);
        passwordLabel.setBounds(75, 150, 75, 25);
        passwordConfirmLabel.setBounds(28, 200, 125, 25);

        usernameField.setBounds(140, 100, 150, 25);
        passwordField.setBounds(140, 150, 150, 25);
        passwordConfirmField.setBounds(140, 200, 150, 25);

        registerButton.setBounds(75, 250, 75, 25);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);
        goBackButton.setBounds(200, 250, 75, 25);
        goBackButton.addActionListener(this);
        goBackButton.setFocusable(false);

        frame.add(registrationLabel);
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(passwordConfirmLabel);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(passwordConfirmField);
        frame.add(registerButton);
        frame.add(goBackButton);
        frame.add(emptyLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackButton) {
            frame.dispose();
            new Auth();
        }

        if (e.getSource() == registerButton) {
            if (String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordConfirmField.getPassword()))) {
                Map<Object, Object> response = new HashMap<>();

                //HTTP
                try {
                    response = postRequest("http://localhost:8080/api/v1/user/register", usernameField.getText(), String.valueOf(passwordField.getPassword()));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //HTTP

                // Server/BD handler
                String[] error = null;
                if (response.get("ErrorMessage") != null) {
                    error = response.get("ErrorMessage").toString().split(" ");
                    switch (error[0]) {
                        case "E11000":
                            JOptionPane.showMessageDialog(frame, "Username is busy", "Sever Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        default:
                            System.out.println(error[0]);
                            return;
                    }
                }
                System.out.println(response);
                frame.dispose();
                new App(response);
            } else {
                JOptionPane.showMessageDialog(frame, "Password mismatch", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
    }
}
