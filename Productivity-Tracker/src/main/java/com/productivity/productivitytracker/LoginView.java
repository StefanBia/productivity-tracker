package com.productivity.productivitytracker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginView {


    JFrame frame = new JFrame("Login");
    JButton logButton = new JButton("Log in");
    JButton registerButton = new JButton("Register new account");
    JLabel labelUser = new JLabel("Username: ");
    JTextField textFieldUser = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();

    JPanelNotOpaque panel1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel2 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel3 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));


    public LoginView(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        frame.setLocation(300, 200);
        frame.setLayout(new GridLayout(3,1));

//        panel1.setOpaque(false);
//        panel2.setOpaque(false);
//        panel3.setOpaque(false);
        int id;


        textFieldUser.setPreferredSize(new Dimension(100, 19));
        textFieldPassword.setPreferredSize(new Dimension(100, 19));
        logButton.setFocusable(false);
        registerButton.setFocusable(false);

        panel1.add(labelUser);
        panel1.add(textFieldUser);
        frame.add(panel1);
        panel2.add(labelPassword);
        panel2.add(textFieldPassword);
        frame.add(panel2);
        panel3.add(registerButton);
        panel3.add(logButton);
        frame.add(panel3);

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

        logButton.setBackground(UIColors.ACCENT.color);
        registerButton.setBackground(UIColors.ACCENT.color);


        logButton.addActionListener(e -> {
            try {
                tryLogin();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        });
        registerButton.addActionListener(e -> registerNew());


    }

    private void registerNew() {
        RegisterView nv = new RegisterView();
        closeView();
    }

    public String getUsername(){
        return textFieldUser.getText();
    }
    public String getPassword(){
        return textFieldPassword.getText();
    }

    public void closeView(){
        this.frame.setVisible(false);
    }

    private void tryLogin() throws MalformedURLException {
        int id = 4;
        if (Objects.equals(textFieldUser.getText(), "")){
            JOptionPane.showMessageDialog(null, "Empty username!");
            return;
        }
        if (Objects.equals(textFieldPassword.getText(), "")){
            JOptionPane.showMessageDialog(null, "Empty password!");
            return;
        }
        // TODO Please Check!
        try {
        String userName = textFieldUser.getText();
        String password = textFieldPassword.getText();

        // Construct the URL with dynamic path parameters
        String apiUrl = "http://localhost:8080/user/" + userName + "/" + password;

        // Create a URL object
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response from the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Print the response
            System.out.println("Response from server:\n" + response.toString());
            JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
            id = Integer.parseInt(jsonObject.getAsNumber("userId").toString());
            System.out.println(id);


        } else {
            System.out.println("Error: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerView timerView = new TimerView();
                timerView.setVisible(true);
            }
        });

        ///TODO
        WeekView nv = new WeekView(id);

        closeView();
    }

}
