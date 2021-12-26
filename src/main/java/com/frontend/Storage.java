package com.frontend;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.frontend.Main.*;

public class Storage implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel;

    JLabel emptyLabel = new JLabel(); // kostil'
    private String Id;
    Container container;


    JButton getShipmentButton = new JButton("Shipment"); // new provider
    JButton backButton = new JButton("Back");

    JSONArray storage;
    JSONObject object;
    String[] header = {"Title", "Description", "Count"};

    JTable storageTable;

    Storage(String id) {
        frame.setTitle("Storage");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        container = frame.getContentPane();
        Id = id;

        try {
            storage = getStorageRequest("http://localhost:8080/api/v1/storage");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(frame, "Server error, try later", "Sever Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[][] rec = new String[storage.length()][3];

        for (int i = 0; i < storage.length(); i++) {
            object = storage.getJSONObject(i);
            rec[i][0] = object.get("title").toString();
            rec[i][1] = object.get("description").toString();
            rec[i][2] = object.get("count").toString();
        }

        storageTable = new JTable(rec, header);
        storageTable.setBounds(0, 20, 550, 100);
        container.setLayout(new BorderLayout());
        container.add(storageTable.getTableHeader(), BorderLayout.PAGE_START);
        container.add(storageTable, BorderLayout.CENTER);

        getShipmentButton.setBounds(575, 70, 100, 25);
        getShipmentButton.addActionListener(this);
        backButton.setBounds(575, 30, 100, 25);
        backButton.addActionListener(this);

        frame.add(getShipmentButton);
        frame.add(backButton);

        // kostil'
        frame.add(emptyLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            frame.dispose();
            new App(Id);
        }

        if (e.getSource() == getShipmentButton) {
            frame.dispose();
            new Shipment(Id);
        }

    }
}
