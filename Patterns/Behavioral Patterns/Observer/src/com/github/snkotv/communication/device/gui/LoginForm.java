package com.github.snkotv.communication.device.gui;

import com.github.snkotv.communication.chats.Account;
import com.github.snkotv.communication.chats.Chat;
import com.github.snkotv.communication.device.Communicator;
import com.github.snkotv.communication.exceptions.InvalidPasswordException;
import com.github.snkotv.communication.exceptions.InvalidUserNameException;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 270;
    private static final int GAP_SIZE = 10;
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 60;
    private static final int TEXT_HEIGHT = 32;
    private static final Font LOGIN_FORM_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font ERROR_MESSAGE_FONT = new Font("Times New Roman", Font.ITALIC, 12);

    private JPanel contentPane;

    private JLabel userNameLabel;
    private JTextField userNameField;
    private JLabel userNameErrorMessage;

    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel passwordErrorMessage;

    private JPanel inputFieldsPanel;

    private void createInputFieldsPanel() {
        inputFieldsPanel = new JPanel();
        inputFieldsPanel.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        inputFieldsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // User name panel
        JPanel userNamePanel = new JPanel();
        userNamePanel.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 3));
        userNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        userNameLabel = new JLabel("Name:");
        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userNameLabel.setFont(LOGIN_FORM_FONT);
        userNameLabel.setPreferredSize(new Dimension(WINDOW_WIDTH / 2 - 20, TEXT_HEIGHT));
        userNamePanel.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setHorizontalAlignment(SwingConstants.CENTER);
        userNameField.setFont(LOGIN_FORM_FONT);
        userNameField.setPreferredSize(new Dimension(WINDOW_WIDTH / 2 - 20, TEXT_HEIGHT));
        userNameField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        userNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                userNameErrorMessage.setText("");
                userNameField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
            }
        });
        userNamePanel.add(userNameField);

        userNameErrorMessage = new JLabel("");
        userNameErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        userNameErrorMessage.setFont(ERROR_MESSAGE_FONT);
        userNameErrorMessage.setForeground(Color.RED);
        userNameErrorMessage.setPreferredSize(new Dimension(WINDOW_WIDTH / 2 - 20, TEXT_HEIGHT));
        userNamePanel.add(userNameErrorMessage);
        inputFieldsPanel.add(userNamePanel);

        // Password panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 3));
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(LOGIN_FORM_FONT);
        passwordLabel.setPreferredSize(new Dimension(WINDOW_WIDTH / 2 - 20, TEXT_HEIGHT));
        passwordPanel.add(passwordLabel);

        passwordField = new JTextField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setFont(LOGIN_FORM_FONT);
        passwordField.setPreferredSize(new Dimension(WINDOW_WIDTH / 2 - 20, TEXT_HEIGHT));
        passwordField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                passwordErrorMessage.setText("");
                passwordField.setBorder(new LineBorder(Color.DARK_GRAY, 1));
            }
        });
        passwordPanel.add(passwordField);

        passwordErrorMessage = new JLabel("");
        passwordErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        passwordErrorMessage.setFont(ERROR_MESSAGE_FONT);
        passwordErrorMessage.setForeground(Color.RED);
        passwordErrorMessage.setPreferredSize(new Dimension(WINDOW_WIDTH / 2 - 20, TEXT_HEIGHT));
        passwordPanel.add(passwordErrorMessage);
        inputFieldsPanel.add(passwordPanel);
        
    }

    private JButton createAccountButton;
    private JButton deleteAccountButton;
    private JButton loginButton;
    private JPanel buttonsPanel;

    private void createButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(WINDOW_WIDTH / 2, WINDOW_HEIGHT));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        createAccountButton = new JButton("Create Account");
        createAccountButton.setHorizontalAlignment(SwingConstants.CENTER);
        createAccountButton.setFont(LOGIN_FORM_FONT);
        createAccountButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!validateFields()) {
                    return;
                }
                String name = userNameField.getText().trim();
                String password = passwordField.getText().trim();
                try {
                    chat.addUser(new Account(name, password, master));
                    JOptionPane.showMessageDialog(null,
                            "Successful registration!!!", "Registration", JOptionPane.INFORMATION_MESSAGE);
                } catch (InvalidUserNameException e) {
                    userNameErrorMessage.setText(e.getMessage());
                    userNameField.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });
        buttonsPanel.add(createAccountButton);

        deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteAccountButton.setFont(LOGIN_FORM_FONT);
        deleteAccountButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!validateFields()) {
                    return;
                }
                String name = userNameField.getText().trim();
                String password = passwordField.getText().trim();
                try {
                    chat.removeUser(new Account(name, password, master));
                    JOptionPane.showMessageDialog(null,
                            "Successful account deletion!!!", "Account deletion", JOptionPane.INFORMATION_MESSAGE);
                } catch (InvalidUserNameException | InvalidPasswordException e) {
                    userNameErrorMessage.setText(e.getMessage());
                    userNameField.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });
        buttonsPanel.add(deleteAccountButton);

        loginButton = new JButton("Log In");
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setFont(LOGIN_FORM_FONT);
        loginButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!validateFields()) {
                    return;
                }
                String name = userNameField.getText().trim();
                String password = passwordField.getText().trim();
                try {
                    chat.logIn(new Account(name, password, master));
                    close();
                } catch (InvalidUserNameException e) {
                    userNameErrorMessage.setText(e.getMessage());
                    userNameField.setBorder(new LineBorder(Color.RED, 1));
                } catch (InvalidPasswordException e) {
                    passwordErrorMessage.setText(e.getMessage());
                    passwordField.setBorder(new LineBorder(Color.RED, 1));
                }
            }
        });
        buttonsPanel.add(loginButton);
    }

    private boolean validateFields() {
        if (userNameField.getText().trim().equals("") && passwordField.getText().trim().equals("")) {
            userNameErrorMessage.setText("This field must be filled up");
            userNameField.setBorder(new LineBorder(Color.RED, 1));
            passwordErrorMessage.setText("This field must be filled up");
            passwordField.setBorder(new LineBorder(Color.RED, 1));
            return false;
        } else if (userNameField.getText().trim().equals("")) {
            userNameErrorMessage.setText("This field must be filled up");
            userNameField.setBorder(new LineBorder(Color.RED, 1));
            return false;
        } else if (passwordField.getText().trim().equals("")) {
            passwordErrorMessage.setText("This field must be filled up");
            passwordField.setBorder(new LineBorder(Color.RED, 1));
            return false;
        }
        return true;
    }

    private Communicator master;
    private Chat chat;

    public LoginForm(Communicator master, Chat chat) {
        this.master = master;
        this.chat = chat;

        setTitle("Manage Accounts");
        setSize(WINDOW_WIDTH + 2 * GAP_SIZE, WINDOW_HEIGHT + 2 * GAP_SIZE);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(GAP_SIZE, GAP_SIZE, GAP_SIZE, GAP_SIZE));
        contentPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        createInputFieldsPanel();
        getContentPane().add(inputFieldsPanel, BorderLayout.WEST);
        createButtonsPanel();
        getContentPane().add(buttonsPanel, BorderLayout.EAST);
    }

    public void open() {
        setVisible(true);
    }

    public void close() {
        dispose();
    }
}
