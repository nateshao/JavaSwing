package com.nateshao.login.test.ui;

import com.nateshao.login.test.models.User;
import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Layout {
    private JPanel Main;
    private JTextField txtuserName;
    private JTextField txtpassWord;
    private JTextField txtAge;
    private JTextField txtOccupation;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField txtid;
    public JTable table1;
    private JScrollPane table_1;

    public static void main(String[] args) {

    }

    public void start() {
        JFrame frame = new JFrame("Layout");
        frame.setContentPane(new Layout().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String send (String action, int ... id) {
        String method = null;
        String responseText = null;

        if (action.equals("users")) {
            method = "GET";
            try {
                URL url = new URL("http://localhost:8080/" + action);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(method);
                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    return response.toString();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (action.equals("save")) {
            method = "POST";
        }
        if (action.equals("delete")) {
            method = "DELETE";
            action = action + "/" + id[0];
        }
        if (action.equals("update")) {
            method = "PUT";
            action = action + "/" + id[0];
        }

        if (action.equals("search")) {
            method = "POST";
            action = action + "/" + id[0];
        }

        try {

            URL url = new URL("http://localhost:8080/" + action);
            System.out.println("http://localhost:8080/" + action);
            System.out.println(method);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String userName = txtuserName.getText();
            String passWord = txtpassWord.getText();
//            String age = txtAge.getText();
//            String occuptaion = txtOccupation.getText();
            String jsonInputString = "{\"userName\": \"" + userName +
                    "\", \"passWord\": \"" + passWord + "" +
                    "\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseText = response.toString();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return responseText;

    }

    public final Object[] columnsHeader = new String[] {"id", "userName", "passWord"};

    public void loadTable() {
        String json = send("users");
        User[] list = new Gson().fromJson(json, User[].class);
//        System.out.println("result is: " + list[0].getuserName());
        int listSize = list.length;
        Object[][] array = new String[listSize][];
        for (int i = 0; i < listSize; i++) {
            array[i] = new String[] {
                    String.valueOf(list[i].getId()),
                    list[i].getuserName(),
                    list[i].getpassWord(),
//                    String.valueOf(list[i].getAge()),
//                    list[i].getOccupation()
            };
        }
        TableModel model = new DefaultTableModel(array, columnsHeader);
        table1.setModel(model);
    }

    public void resetFields() {
//        txtAge.setText("");
        txtid.setText("");
        txtuserName.setText("");
        txtpassWord.setText("");
//        txtOccupation.setText("");
        txtuserName.requestFocus();
    }

    public Layout() {
        loadTable();
        saveButton.addActionListener(e -> {
            String response = send("save");
            JOptionPane.showMessageDialog(null, response);
            loadTable();
            resetFields();
        });


        updateButton.addActionListener(e -> {
            int id = Integer.parseInt(txtid.getText());
            String response = send("update", id);
            JOptionPane.showMessageDialog(null, response);
            loadTable();
            resetFields();
        });


//        deleteButton.addActionListener(e -> {
//            int id = Integer.parseInt(txtid.getText());
//            String response = send("delete", id);
//            JOptionPane.showMessageDialog(null, response);
//            loadTable();
//            resetFields();
//        });


//        searchButton.addActionListener(e -> {
//            int id = Integer.parseInt(txtid.getText());
//            String responseJson = send("search", id);
//            User user = new Gson().fromJson(responseJson, User.class);
//            txtAge.setText(String.valueOf(user.getAge()));
//            txtuserName.setText(user.getuserName());
//            txtpassWord.setText(user.getpassWord());
//            txtOccupation.setText(user.getOccupation());
//            JOptionPane.showMessageDialog(null, "Search button pressed");
//        });
    }

}
