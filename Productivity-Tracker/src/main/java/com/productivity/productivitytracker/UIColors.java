package com.productivity.productivitytracker;

import java.awt.*;

public enum UIColors {
    BACKGROUND(new Color(175, 190, 162)),
    RESULT(new Color(156, 180, 172));

    final public Color color;

    UIColors (Color col){
        this.color = col;
    }
}
