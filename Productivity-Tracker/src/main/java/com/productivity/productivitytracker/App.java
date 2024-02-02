package com.productivity.productivitytracker;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;

public class App extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        JOptionPane.showMessageDialog(null, "info", "Alert", JOptionPane.INFORMATION_MESSAGE);

    }
}
