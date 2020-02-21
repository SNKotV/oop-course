package com.github.snkotv;

import javax.swing.*;

public class MainWindow extends Window {

    private static MainWindow instance = null;

    public MainWindow() {
        super("Caesar Cipher");
    }

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }



}
