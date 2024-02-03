package com.productivity.productivitytracker;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RegisterView {

    JFrame frame = new JFrame("Register new user");
    JButton registerButton = new JButton("Register the account");
    JLabel labelUser = new JLabel("Username: ");
    JTextField textFieldUser = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();

    JPanelNotOpaque panel1 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel2 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel3 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panelAux = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));
    JPanelNotOpaque panel4 = new JPanelNotOpaque(new FlowLayout(FlowLayout.RIGHT));

    public RegisterView(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(350, 350));
        frame.setLocation(300, 200);

        //frame.setLayout(new GridLayout(3,1));

        textFieldUser.setPreferredSize(new Dimension(140, 22));
        textFieldPassword.setPreferredSize(new Dimension(140, 22));
        registerButton.setFocusable(false);
        registerButton.setPreferredSize(new Dimension(200, 30));


        panel1.add(labelUser);
        panel1.add(textFieldUser);
        panelAux.add(panel1);
        panel3.add(labelPassword);
        panel3.add(textFieldPassword);
        panelAux.add(panel3);
        frame.add(panelAux, BorderLayout.CENTER);

        panel4.add(registerButton);
        frame.add(panel4, BorderLayout.SOUTH);

        registerButton.setBackground(UIColors.ACCENT.color);

        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);
        frame.setVisible(true);

        registerButton.addActionListener(e -> checkFields());

    }

    private void checkFields() {
        if (Objects.equals(textFieldUser.getText(), "")){
            JOptionPane.showMessageDialog(null, "Empty username!");
            return;
        }
        if (Objects.equals(textFieldPassword.getText(), "")){
            JOptionPane.showMessageDialog(null, "Empty password!");
            return;
        }
        // TODO please check!
        LoginView nv = new LoginView();
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

}
