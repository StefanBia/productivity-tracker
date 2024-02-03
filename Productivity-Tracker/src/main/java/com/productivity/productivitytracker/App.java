package com.productivity.productivitytracker;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // Create and launch JavaFX application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerView timerView = new TimerView();
                timerView.setVisible(true);
                System.out.println(getActualLines("/home/stefan/Stefan Projects/productivity-tracker/Productivity-Tracker"));
            }
        });
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
