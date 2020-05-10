package com.github.snkotv.communication.device;

import com.github.snkotv.communication.User;
import com.github.snkotv.communication.chats.Chat;
import com.github.snkotv.communication.device.gui.ChatScreen;
import com.github.snkotv.communication.device.gui.LoginForm;
import com.github.snkotv.communication.device.gui.MainScreen;
import com.github.snkotv.communication.device.gui.Screen;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Communicator extends JFrame {
    private static int availableID = 1;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 520;
    private static final int GAP_SIZE = 10;
    private static final int USERNAME_HEIGHT= 32;

    private int ID;
    private JPanel contentPane;
    private MainScreen mainScreen;

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    private ChatScreen chatScreen;

    public ChatScreen getChatScreen() {
        return chatScreen;
    }

    private Screen activeScreen;

    public Screen getActiveScreen() {
        return activeScreen;
    }

    private Communicator() {
        ID = availableID++;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Communication Device Version 1.0");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        contentPane.setSize(new Dimension(WINDOW_WIDTH - 2 * GAP_SIZE, WINDOW_HEIGHT - 2 * GAP_SIZE));
        Border padding = BorderFactory.createEmptyBorder(GAP_SIZE, GAP_SIZE, GAP_SIZE, GAP_SIZE);
        contentPane.setBorder(padding);
        setContentPane(contentPane);
    }

    public Communicator(User owner) {
        this();
        // Adding user name and surname on frame
        Font userNameFont = new Font("Arial", Font.ITALIC, 16);
        JLabel userName = new JLabel(owner.getName() + " " +owner.getSurname());
        userName.setPreferredSize(new Dimension(WINDOW_WIDTH - 2 * GAP_SIZE, USERNAME_HEIGHT));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setFont(userNameFont);
        userName.setBorder(new LineBorder(Color.BLACK, 1));
        getContentPane().add(userName);
        // Creation screens and selecting main screen as initial
        Dimension screenSizes = new Dimension(getContentPane().getWidth(), getContentPane().getHeight() - USERNAME_HEIGHT - 4 * GAP_SIZE);
        mainScreen = new MainScreen(this, screenSizes);
        chatScreen = new ChatScreen(this, screenSizes);
        getContentPane().add(chatScreen);
        chatScreen.setVisible(false);
        getContentPane().add(mainScreen);
        activeScreen = mainScreen;
    }

    public boolean equals(Communicator device) {
        return ID == device.ID;
    }

    public void run() {
        setVisible(true);
    }

    public void shutdown() {
        dispose();
    }

    public boolean isActive() {
        return isDisplayable();
    }

    public void enterChat(Chat chat) {
        if (!chat.isConnected(this)) {
            new LoginForm(this, chat).open();
            return;
        }
        chat.display(this);
        activeScreen = chatScreen;
        chatScreen.setVisible(true);
    }

    public void toMainScreen() {
        chatScreen.setVisible(false);
        activeScreen = mainScreen;
    }
}
