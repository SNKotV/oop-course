package com.github.snkotv.communication.device.gui;

import com.github.snkotv.communication.device.Communicator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatScreen extends Screen {
    private static final int TITLE_WIDTH = 300;
    private static final int TITLE_HEIGHT = 24;
    private static final int INTERACTIVE_PANEL_HEIGHT = 75;
    private static final Font CHAT_SCREEN_TITLES_FONT = new Font("Times New Roman", Font.BOLD, 18);
    private static final Font CHAT_SCREEN_MESSAGES_FONT = new Font("Times New Roman", Font.BOLD, 14);

    private JLabel chatTitle;
    private JLabel userName;
    private JTextArea textArea;
    private class InteractivePanel extends JPanel {
        private static final int MESSAGE_HEIGHT = 24;
        private static final int SQUARE_BUTTON_SIZE = 60;
        
        private JPanel messageSendingPanel;
        private JTextField inputTextField;
        private JButton sendButton;
        private JButton clearHistoryButton;
        private JPanel outButtonsPanel;
        private JButton exitButton;
        private JButton logoutButton;

        public InteractivePanel(Dimension dimension) {
            setPreferredSize(dimension);
            setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

            // Adding message sending panel
            messageSendingPanel = new JPanel();
            messageSendingPanel.setPreferredSize(new Dimension(dimension.width - 150, INTERACTIVE_PANEL_HEIGHT));
            messageSendingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            // Creation of input text field
            inputTextField = new JTextField();
            int inputTextFieldWidth = dimension.width - 160;
            inputTextField.setPreferredSize(new Dimension(inputTextFieldWidth, MESSAGE_HEIGHT));
            inputTextField.setFont(CHAT_SCREEN_MESSAGES_FONT);
            messageSendingPanel.add(inputTextField);

            // Send message button
            sendButton = new JButton("Send");
            sendButton.setFont(CHAT_SCREEN_MESSAGES_FONT);
            sendButton.setHorizontalAlignment(SwingConstants.CENTER);
            sendButton.setPreferredSize(new Dimension(inputTextFieldWidth / 2, MESSAGE_HEIGHT + 10));
            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    textArea.append(inputTextField.getText() + "\n");
                }
            });
            messageSendingPanel.add(sendButton);

            // clear history button
            clearHistoryButton = new JButton("Clear Hist.");
            clearHistoryButton.setFont(CHAT_SCREEN_MESSAGES_FONT);
            clearHistoryButton.setHorizontalAlignment(SwingConstants.CENTER);
            clearHistoryButton.setPreferredSize(new Dimension(inputTextFieldWidth / 2, MESSAGE_HEIGHT + 10));
            clearHistoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Clear");
                }
            });
            messageSendingPanel.add(clearHistoryButton);
            add(messageSendingPanel, BorderLayout.WEST);

            // exit, logout buttons panel
            outButtonsPanel = new JPanel();
            outButtonsPanel.setPreferredSize(new Dimension(150, INTERACTIVE_PANEL_HEIGHT));
            outButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            // exit button
            exitButton = new JButton("Exit");
            exitButton.setFont(CHAT_SCREEN_MESSAGES_FONT);
            exitButton.setHorizontalAlignment(SwingConstants.CENTER);
            exitButton.setPreferredSize(new Dimension(SQUARE_BUTTON_SIZE, SQUARE_BUTTON_SIZE));
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    master.toMainScreen();
                }
            });
            outButtonsPanel.add(exitButton);

            // logout button
            logoutButton = new JButton("Log Out");
            logoutButton.setFont(CHAT_SCREEN_MESSAGES_FONT);
            logoutButton.setHorizontalAlignment(SwingConstants.CENTER);
            logoutButton.setPreferredSize(new Dimension(SQUARE_BUTTON_SIZE, SQUARE_BUTTON_SIZE));
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("LogOut");
                }
            });
            outButtonsPanel.add(logoutButton);
            add(outButtonsPanel, BorderLayout.EAST);
        }
    };

    InteractivePanel interactivePanel;

    public ChatScreen(Communicator master, Dimension d) {
        super(master, d);

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // Creation of chat title label
        chatTitle = new JLabel("Title test");
        chatTitle.setHorizontalAlignment(SwingConstants.CENTER);
        chatTitle.setBorder(BorderFactory.createRaisedBevelBorder());
        chatTitle.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT));
        chatTitle.setFont(CHAT_SCREEN_TITLES_FONT);
        add(chatTitle);

        // Creation of user name label
        userName = new JLabel("Nick test");
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setBorder(BorderFactory.createLoweredBevelBorder());
        userName.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT));
        userName.setFont(CHAT_SCREEN_TITLES_FONT);
        add(userName);

        // Adding messages text area
        textArea = new JTextArea();
        textArea.setFont(CHAT_SCREEN_MESSAGES_FONT);
        textArea.setEditable(false);
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        Dimension scrollAreaSize =
                new Dimension(getPreferredSize().width - 10,
                        getPreferredSize().height - INTERACTIVE_PANEL_HEIGHT - 2 * TITLE_HEIGHT - 40);
        scrollableTextArea.setPreferredSize(scrollAreaSize);
        add(scrollableTextArea);

        // Adding interactive panel
        interactivePanel =
                new InteractivePanel(new Dimension(getPreferredSize().width - 4, INTERACTIVE_PANEL_HEIGHT));
        add(interactivePanel);
    }
}
