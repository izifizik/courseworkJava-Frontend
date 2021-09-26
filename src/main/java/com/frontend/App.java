package com.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.frontend.Main.getRequest;

public class App implements ActionListener {
    JFrame frame = new JFrame();

    JLabel emptyLabel = new JLabel(); // kostil'

    JLabel usernameLabel = new JLabel();
    JLabel idLabel = new JLabel();
    JLabel title = new JLabel();

    JPanel panel = new JPanel(new FlowLayout());

    JButton userBioButton = new JButton("User");
    JButton eventsListButton = new JButton("Events");
    JButton putButton = new JButton("Change data");


    App(Map<Object, Object> response) {
        // frame start
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Map<Object, Object> user = new HashMap<>();

        try {
            user = getRequest("http://localhost:8080/api/v1/user", response.get("id").toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //label set
        usernameLabel.setText("Username: " + user.get("username").toString());
        idLabel.setText("id(decorate): " + user.get("id").toString());
        title.setText("User BIO");
        title.setFont(new Font(null, Font.ITALIC, 25));
        panel.setBackground(new Color(0x00FFFF));

        //entity location
        title.setBounds(100, 5, 200, 25);
        usernameLabel.setBounds(20, 50, 100, 100);
        idLabel.setBounds(20, 90, 300, 100);
        putButton.setBounds(150, 250, 115, 30);
        putButton.addActionListener(this);
        putButton.setFocusable(false);

        //panel add
        panel.add(eventsListButton);
        panel.add(userBioButton);

        //frame add
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(title);
        frame.add(usernameLabel);
        frame.add(idLabel);
        frame.add(putButton);


        // kostil'
        frame.add(emptyLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
