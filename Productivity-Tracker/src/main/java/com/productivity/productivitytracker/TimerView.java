package com.productivity.productivitytracker;

import com.productivity.productivitytracker.UIColors;

import javax.swing.*;
import java.awt.*;

public class TimerView {

    //LoginController controller = new LoginController();

    JFrame frame = new JFrame("Login");
    JButton logButton = new JButton("Log in");
    JButton registerButton = new JButton("Register new account");
    JLabel labelUser = new JLabel("Username: ");
    JTextField textFieldUser = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();

    JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));


    public TimerView(){
        //controller.setView(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        frame.setLocation(300, 200);
        frame.setLayout(new GridLayout(3,1));

//        panel1.setOpaque(false);
//        panel2.setOpaque(false);
//        panel3.setOpaque(false);


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

        //logButton.addActionListener(e -> controller.checkUser());
        //registerButton.addActionListener(e -> controller.registerNew());


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

}
