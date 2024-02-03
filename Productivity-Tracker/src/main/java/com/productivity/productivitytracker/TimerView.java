package com.productivity.productivitytracker;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class TimerView extends JFrame {

    private Timer timer;
    private Timer countdownTimer;
    private int seconds = 0;
    private int countdownSeconds = 1500; // 25 minutes initially

    private JLabel timerLabel;
    private JLabel countdownLabel;

    private JButton startStopButton;
    private JButton statsButton;
    private JTextField pathTextField = new JTextField();
    private JLabel pathLabel = new JLabel("Path to the project you want to track:");
    private JLabel linesWrittenLabel = new JLabel("");
    private int initialLines;
    private int finalLines;



    JLabel labelWork = new JLabel("Work time: ");
    JTextField textFieldWork = new JTextField("25");
    JLabel labelBreak = new JLabel("Break time: ");
    JTextField textFieldBreak = new JTextField("5");

    JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private boolean isRunning = false;
    private boolean isMainTimerPaused = false;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimerView() {
        setTitle("Timer Example");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        timerLabel = new JLabel("Time: 0:00:00", SwingConstants.CENTER);
        countdownLabel = new JLabel("Countdown: 25:00", SwingConstants.CENTER);

        startStopButton = new JButton("Start");
        statsButton = new JButton("View stats");
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startStopTimer();
            }
        });

        statsButton.addActionListener(e -> openStats());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(timerLabel);
        panel.add(countdownLabel);
        panel.add(startStopButton);
        panel.add(statsButton);

        textFieldWork.setPreferredSize(new Dimension(65, 20));
        textFieldBreak.setPreferredSize(new Dimension(65, 20));
        panel1.add(labelWork);
        panel1.add(textFieldWork);
        panel.add(panel1);
        panel2.add(labelBreak);
        panel2.add(textFieldBreak);
        panel.add(panel2);

        JPanel panell = new JPanel();
        panell.add(pathLabel);
        pathTextField.setPreferredSize( new Dimension(65, 20) );
        panell.add(pathTextField);
        panell.add(linesWrittenLabel);

        panel.add(panell);

        add(panel);
    }

    private void openStats() {
        WeekView nv = new WeekView(4);
    }

    private void startStopTimer() {
        if (!isRunning) {
            startTimers();
        } else {
            stopTimers();
        }
    }

    private void startTimers() {
        isRunning = true;
        startStopButton.setText("Stop");
        startTime = LocalDateTime.now();
        if(!pathTextField.getText().equals("")){
            initialLines = getActualLines(pathTextField.getText());
        }
        else{
            initialLines = 0;
        }

        if (isMainTimerPaused) {
            timer.restart();
        } else {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    seconds++;
                    updateTimerLabel();
                }
            });
        }

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        int downSecs;
        try{
            downSecs = Integer.parseInt(textFieldWork.getText());
            countdownSeconds = downSecs * 60;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Invalid work time input. Set by default to 25 minutes.");
            countdownSeconds = 1500;
        }

        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownSeconds--;
                updateCountdownLabel();
                if (countdownSeconds == 0) {
                    stopTimers();
                }
            }
        });

        timer.start();
        countdownTimer.start();
        isMainTimerPaused = false;
    }

    private void stopTimers() {
        isRunning = false;
        startStopButton.setText("Start");
        endTime = LocalDateTime.now();
        System.out.println("Start: " + startTime+'\n'+"Stop: " + endTime);

        if(!pathTextField.getText().equals("")){
            finalLines = getActualLines(pathTextField.getText());
            if(finalLines - initialLines < 0)
            {
                finalLines = 0;
                initialLines = 0;
            }
            linesWrittenLabel.setText(Integer.toString(finalLines - initialLines) + " lines written");
        }
        else{
            finalLines = 0;
        }

        if (timer != null) {
            timer.stop();
            isMainTimerPaused = true;
        }

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        int downSecs;
        try{
            downSecs = Integer.parseInt(textFieldBreak.getText());
            countdownSeconds = downSecs * 60;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Invalid break time input. Set by default to 5 minutes.");
            countdownSeconds = 300;
        }

        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownSeconds--;
                updateCountdownLabel();
                if (countdownSeconds == 0) {
                    stopTimers();
                    JOptionPane.showMessageDialog(null,"Time Ended");
                }
            }
        });

        countdownTimer.start();

        updateCountdownLabel();
    }

    private void updateTimerLabel() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String timeString = String.format("Time: %02d:%02d:%02d", hours, minutes, secs);
        timerLabel.setText(timeString);
    }

    private void updateCountdownLabel() {
        int minutes = countdownSeconds / 60;
        int secs = countdownSeconds % 60;

        String countdownString = String.format("Countdown: %02d:%02d", minutes, secs);
        countdownLabel.setText(countdownString);
    }
    public static int getActualLines(String path){
        File projectDirectory = new File(path);
        int lineCount = 0;
        for (File file : projectDirectory.listFiles()) {
            if (file.isFile() && (file.getName().endsWith(".java") )) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while (reader.readLine() != null) {
                        lineCount++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(file.isDirectory()){
                lineCount += getActualLines(file.getPath());
            }
        }
        return lineCount;
    }


}