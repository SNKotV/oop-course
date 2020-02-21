package com.github.snkotv;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final int DEFAULT_WIDTH = 480;
    private static final int DEFAULT_HEIGHT = 360;

    public Window() {
        this("", DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Window(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Window(String title, int width, int height) {
        super(title);
        init(width, height);
    }


    private void init(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
