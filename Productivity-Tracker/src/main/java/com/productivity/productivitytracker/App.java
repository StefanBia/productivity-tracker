package com.productivity.productivitytracker;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;


import javax.swing.*;

public class App extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        // Create and launch JavaFX application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerView timerView = new TimerView();
                timerView.setVisible(true);
            }
        });
    }

}
