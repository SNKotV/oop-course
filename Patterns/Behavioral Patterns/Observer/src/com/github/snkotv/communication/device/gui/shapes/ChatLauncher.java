package com.github.snkotv.communication.device.gui.shapes;

import com.github.snkotv.communication.chats.Chat;
import com.github.snkotv.communication.device.gui.MainScreen;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatLauncher extends JPanel {
    private static final int WIDTH = 360;
    private static final int HEIGHT = 100;
    private static final int TITLE_WIDTH = 200;
    private static final int TITLE_HEIGHT = 60;
    private static final int NOTIFICATIONS_LABEL_WIDTH = 60;
    private static final int NOTIFICATIONS_LABEL_HEIGHT = 60;
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 60;
    private static final Font CHAT_LAUNCHER_FONT = new Font("Times New Roman", Font.BOLD, 32);

    private MainScreen master;
    private Chat chat;
    private JLabel title;
    private JLabel notifications;
    private JButton enterButton;

    public String getName() {
        return chat.getName();
    }

    public ChatLauncher(MainScreen master, Chat chat, Color titleColor) {
        this.master = master;
        this.chat = chat;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(new LineBorder(Color.BLACK, 1));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        title = new JLabel(chat.getName());
        title.setForeground(titleColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT));
        title.setBorder(new LineBorder(Color.BLACK, 1));
        title.setFont(CHAT_LAUNCHER_FONT);
        add(title);

        notifications = new JLabel("0");
        notifications.setHorizontalAlignment(SwingConstants.CENTER);
        notifications.setPreferredSize(new Dimension(NOTIFICATIONS_LABEL_WIDTH, NOTIFICATIONS_LABEL_HEIGHT));
        notifications.setBorder(new LineBorder(Color.BLACK, 1));
        notifications.setFont(CHAT_LAUNCHER_FONT);
        add(notifications);

        enterButton = new JButton();
        enterButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        enterButton.setIcon(new ImageIcon("C:\\Users\\sergn\\Projects\\OOP Course" +
                "\\Patterns\\Behavioral Patterns\\Observer\\src" +
                "\\com\\github\\snkotv\\communication\\device\\gui\\shapes\\imgs\\door.jpg"));
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                master.enterChat(chat);
            }
        });
        add(enterButton);
    }

    public void updateUnreadMessagesNumber(int number) {
        notifications.setText("" + number);
    }

}
