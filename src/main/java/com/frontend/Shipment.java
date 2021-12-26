package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.frontend.Main.postStorageRequest;

public class Shipment implements ActionListener {
    String Id;
    JFrame frame = new JFrame();

    JLabel emptyLable = new JLabel(); // kostil'
    JLabel titleLabel = new JLabel("Set Title");
    JLabel descriptionLabel = new JLabel("Set Description");
    JLabel countLable = new JLabel("Set Count");

    JTextField titleField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField countField = new JTextField();

    JButton backButton = new JButton("Back");
    JButton sendDataButton = new JButton("Send");


    public Shipment(String id) {
        Id = id;
        frame.setTitle("Shipment");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        titleLabel.setBounds(20, 20, 75, 25);
        descriptionLabel.setBounds(20, 45, 120, 25);
        countLable.setBounds(20, 70, 75, 25);

        titleField.setBounds(120, 20, 125, 25);
        descriptionField.setBounds(120, 45, 125, 25);
        countField.setBounds(120, 70, 125, 25);

        backButton.setBounds(20, 150, 100, 25);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        sendDataButton.setBounds(20, 100, 100, 25);
        sendDataButton.addActionListener(this);
        sendDataButton.setFocusable(false);

        frame.add(titleLabel);
        frame.add(descriptionLabel);
        frame.add(countLable);
        frame.add(titleField);
        frame.add(descriptionField);
        frame.add(countField);
        frame.add(backButton);
        frame.add(sendDataButton);

        frame.add(emptyLable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new Storage(Id);
        }

        if (e.getSource() == sendDataButton) {
            Map<Object, Object> response = new HashMap<>();
            try {
                response = postStorageRequest("http://localhost:8080/api/v1/storage", titleField.getText(), descriptionField.getText(), Integer.parseInt(countField.getText()));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (response.get("ErrorMessage") != null) {
                JOptionPane.showMessageDialog(frame, response.get("ErrorMessage").toString(), "Sever Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            frame.dispose();
            new Storage(Id);
        }
    }
}
