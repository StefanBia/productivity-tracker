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
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                TimerView timerView = new TimerView();
//                timerView.setVisible(true);
//                System.out.println(getActualLines("/home/stefan/Stefan Projects/productivity-tracker/Productivity-Tracker"));
//            }
//        });

        LoginView firstView = new LoginView();

    }



}
