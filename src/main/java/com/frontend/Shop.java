package com.frontend;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.frontend.Main.*;

public class Shop implements ActionListener {
    JFrame frame = new JFrame();

    String Id;

    JLabel emptyLabel = new JLabel(); // kostil'
    Container container;

    JButton backButton = new JButton("Back");

    JSONArray shop;
    JSONObject object;
    String[] header = {"Title", "Description", "Count", "Price"};

    JTable shopTable;

    JCheckBox firstBox = new JCheckBox();

    Shop(String id) {
        frame.setTitle("Shop");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        container = frame.getContentPane();
        Id = id;

        try {
            shop = getShopRequest("http://localhost:8080/api/v1/shop");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[][] rec = new String[shop.length()][4];

        for (int i = 0; i < shop.length(); i++) {
            object = shop.getJSONObject(i);
            rec[i][0] = object.get("title").toString();
            rec[i][1] = object.get("description").toString();
            rec[i][2] = object.get("count").toString();
            rec[i][3] = object.get("price").toString();
        }

        shopTable = new JTable(rec, header);
        shopTable.setBounds(0, 20, 550, 100);
        container.setLayout(new BorderLayout());
        container.add(shopTable.getTableHeader(), BorderLayout.PAGE_START);
        container.add(shopTable, BorderLayout.CENTER);

        backButton.setBounds(575, 30, 150, 25);
        backButton.addActionListener(this);

        frame.add(backButton);

        frame.add(emptyLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new App(Id);
        }
    }
}
