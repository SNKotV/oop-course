package com.github.snkotv.communication.device.gui;

import com.github.snkotv.communication.device.Communicator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Screen extends JPanel {
    protected Communicator master;

    public Screen(Communicator master) {
        this.master = master;
        setBorder(new LineBorder(Color.BLACK, 1));
        setBackground(Color.WHITE);
    }

    public Screen(Communicator master, Dimension d) {
        this(master);
        setPreferredSize(d);
    }
}
