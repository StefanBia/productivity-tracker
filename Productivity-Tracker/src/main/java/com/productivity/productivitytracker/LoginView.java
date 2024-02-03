package com.productivity.productivitytracker;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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


        labelUser.setForeground(UIColors.BLACK_TEXT.color);
        labelPassword.setForeground(UIColors.BLACK_TEXT.color);


        textFieldUser.setPreferredSize(new Dimension(100, 22));
        textFieldPassword.setPreferredSize(new Dimension(100, 22));
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


        logButton.addActionListener(e -> tryLogin());
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

    private void tryLogin(){
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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerView timerView = new TimerView();
                timerView.setVisible(true);
            }
        });

//        WeekView nv = new WeekView(id);

        closeView();
    }

}
