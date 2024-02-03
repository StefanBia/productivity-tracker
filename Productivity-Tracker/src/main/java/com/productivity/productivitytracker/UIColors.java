package com.productivity.productivitytracker;

import java.awt.*;

public enum UIColors {
    BACKGROUND(new Color(254, 250, 224)),
    ACCENT(new Color(221, 161, 94)),
    TEXT(new Color(188, 108, 37)),
    RESULT(new Color(96, 108, 56));

    final public Color color;

    UIColors (Color col){
        this.color = col;
    }
}

