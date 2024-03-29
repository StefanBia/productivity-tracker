package com.productivity.productivitytracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.remoteDev.downloader.TestJetBrainsClientDownloaderConfigurationProvider;
import com.productivity.productivitytracker.entity.Work;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class WeekView {

    JFrame frame = new JFrame("Your stats");

    private int userId;


    ArrayList<ArrayList<Integer>> intervals = new ArrayList<ArrayList<Integer>>();//// datestop-datestart




    ArrayList<Integer> dates  = new ArrayList<Integer>(Arrays.asList(7, 8, 9, 10, 11, 12, 13));
    ArrayList<Integer> times  = new ArrayList<Integer>(Arrays.asList(44, 89, 3, 55, 45, 88, 76));

    final int LABEL_HEIGHT = 45;
    final int LABEL_WIDTH = 90;
    final int SPACE = 5;
    final int RESULT_SPACING = 2;
    final int W = 700 + 5;
    int H = 800;

    int y, x;


    public WeekView(int userId){
        this.userId=userId;
        ///API Call
        try {

            // Construct the URL with dynamic path parameters
            String apiUrl = "http://localhost:8080/user/worksBetween/" + userId;
            /*
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
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                ObjectMapper objectMapper = new ObjectMapper();
                ArrayList<Work> workList = objectMapper.readValue(response.toString(), new TypeReference<ArrayList<Work>>() {});


            } else {
                System.out.println("Error: " + responseCode);
            }

            // Close the connection
            connection.disconnect();


             */


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBackground(UIColors.BACKGROUND.color);
        frame.getContentPane().setBackground(UIColors.BACKGROUND.color);

        for(int i = 0; i < 7; i++){
            ArrayList<Integer> a = new ArrayList<>();
            intervals.add(a);
        }

        intervals.set(0, new ArrayList<Integer>(Arrays.asList(35, 40, 22, 170)));
        intervals.set(1, new ArrayList<Integer>(Arrays.asList(65, 20)));
        intervals.set(4, new ArrayList<Integer>(Arrays.asList(15, 15, 15, 20, 15)));
        intervals.set(6, new ArrayList<Integer>(Arrays.asList(15)));

        int max = 0;
        for(int i = 0; i < 7; i++){
            int s = 0;
            for(Integer l : intervals.get(i)){
                s += l;
            }
            times.set(i, s);
            if(s > max)
                max = s;
        }

        H = max + 100;
        H += 2*LABEL_HEIGHT+4*SPACE+50;

        frame.setSize(W, H);


        for(int i = 0; i<7; i++){
            x = i*100 + SPACE;
            y = H - SPACE - LABEL_HEIGHT - LABEL_HEIGHT;
//            System.out.println("Y = " + y);
            JLabel l = new JLabel(String.valueOf(dates.get(i)) + ".01" );
            l.setLayout(null);
            l.setHorizontalAlignment(SwingConstants.CENTER);
            l.setFont(new Font(l.getFont().getName(), Font.BOLD, 15));
            l.setBackground(UIColors.ACCENT.color);
            l.setForeground(UIColors.BLACK_TEXT.color);
            //l.setOpaque(true);
            l.setBounds(x, y, LABEL_WIDTH, LABEL_HEIGHT);
            frame.add(l);

            y -= (SPACE + LABEL_HEIGHT);
            JLabel l1 = new JLabel(String.valueOf(times.get(i)) + " min.");
            l1.setLayout(null);
            l1.setHorizontalAlignment(SwingConstants.CENTER);
            l1.setBackground(UIColors.ACCENT.color);
            l1.setForeground(UIColors.BLACK_TEXT.color);
            //l1.setOpaque(true);
            l1.setBounds(x, y, LABEL_WIDTH, LABEL_HEIGHT);
            frame.add(l1);

            for(Integer interval : intervals.get(i)){
                y -= (RESULT_SPACING + interval);
                JLabel p = new JLabel(interval + " min.");
                p.setBackground(UIColors.ACCENT.color);
                p.setForeground(UIColors.BLACK_TEXT.color);
//                p.setForeground(UIColors.TEXT.color);
                p.setFont(new Font(p.getFont().getName(), Font.PLAIN, 11));
                p.setHorizontalAlignment(SwingConstants.CENTER);
                p.setOpaque(true);
                p.setLayout(null);
                p.setBounds(x, y, LABEL_WIDTH, interval);
                frame.add(p);
            }

        }

        JButton backButton = new JButton("<");
        backButton.setFocusable(false);
        JButton nextButton = new JButton(">");
        nextButton.setFocusable(false);
        Font largerFont = new Font(nextButton.getFont().getName(), Font.PLAIN, 18);
        backButton.setFont(largerFont);
        nextButton.setFont(largerFont);
//        backButton.setBackground(UIColors.RESULT.color);
//        nextButton.setBackground(UIColors.RESULT.color);
        backButton.setForeground(UIColors.BACKGROUND.color);
        nextButton.setForeground(UIColors.BACKGROUND.color);
        backButton.addActionListener(e -> goBack());
        backButton.setLayout(null);
        nextButton.setLayout(null);
        backButton.setBounds(100+SPACE, SPACE, LABEL_WIDTH/2, LABEL_HEIGHT/2);
        nextButton.setBounds(W-100-LABEL_WIDTH/2-SPACE, SPACE, LABEL_WIDTH/2, LABEL_HEIGHT/2);
        frame.add(backButton);
        frame.add(nextButton);




            // dummy for fixing a bug
        JLabel l = new JLabel("");
        l.setLayout(null);
        l.setBounds(x, y, 50, 50);
        frame.add(l);

        frame.setVisible(true);
    }

    private void goBack() {
        WeekView2 nv = new WeekView2(4);
        frame.setVisible(false);
    }

}


