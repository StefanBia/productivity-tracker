package com.productivity.productivitytracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerView extends JFrame {

    private Timer timer;
    private int seconds = 0;

    private JLabel timerLabel;

    public TimerView() {
        setTitle("Timer Example");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timerLabel = new JLabel("Time: 0:00:00", SwingConstants.CENTER);
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton openClassView1Button = new JButton("Open ClassView1");
        JButton openClassView2Button = new JButton("Open ClassView2");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });



        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                updateTimerLabel();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(timerLabel);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(openClassView1Button);
        panel.add(openClassView2Button);

        add(panel);
    }

    private void startTimer() {
        timer.start();
    }

    private void stopTimer() {
        timer.stop();
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String timeString = String.format("Time: %02d:%02d:%02d", hours, minutes, secs);
        timerLabel.setText(timeString);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerView().setVisible(true);
            }
        });
    }
}
