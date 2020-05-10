package com.github.snkotv.communication.device.gui;

import com.github.snkotv.communication.chats.BlueChat;
import com.github.snkotv.communication.chats.Chat;
import com.github.snkotv.communication.chats.GreenChat;
import com.github.snkotv.communication.chats.RedChat;
import com.github.snkotv.communication.device.Communicator;
import com.github.snkotv.communication.device.gui.shapes.ChatLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends Screen {
    private ChatLauncher redChat;
    private ChatLauncher greenChat;
    private ChatLauncher blueChat;

    public MainScreen(Communicator master, Dimension d) {
        super(master, d);
        setLayout(new FlowLayout(0, 10, 30));
        redChat = new ChatLauncher(this, RedChat.getInstance(), Color.RED);
        add(redChat);
        greenChat = new ChatLauncher(this, GreenChat.getInstance(), Color.GREEN);
        add(greenChat);
        blueChat = new ChatLauncher(this, BlueChat.getInstance(), Color.BLUE);
        add(blueChat);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(master.getName());
            }
        });
    }

    public void enterChat(Chat chat) {
        master.enterChat(chat);
    }
}
