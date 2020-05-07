package com.github.snkotv;

import com.github.snkotv.filesystem.FileSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Callable;

public class Terminal extends JFrame {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private static Terminal instance = null;
    private static CommandExecutor commandExecutor;

    public static Terminal getInstance() {
        if (instance == null) {
            instance = new Terminal(WINDOW_WIDTH, WINDOW_HEIGHT);
        }
        return instance;
    }

    private Font defaultFont = new Font("Arial", Font.PLAIN,16);

    private JTextArea clientArea;
    private JTextField commandLine;

    public JTextArea getClientArea() {
        return clientArea;
    }


    public Terminal(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        init();
        commandExecutor = new CommandExecutor(this);
    }

    private void init() {
        addClientArea();
        addCommandExecutorLine();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addClientArea() {
        clientArea = new JTextArea();
        clientArea.setFont(defaultFont);
        clientArea.setLineWrap(true);
        clientArea.setWrapStyleWord(true);
        clientArea.setEditable(false);
        clientArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    clientArea.setEditable(false);
                    commandLine.setEditable(true);
                    commandLine.grabFocus();
                }
            }
        });
        clientArea.setText(FileSystem.getInstance().getCurrentDirectoryPointer().getFullName() + "> ");
        JScrollPane scrollableClientArea = new JScrollPane(clientArea);
        scrollableClientArea.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        getContentPane().add(scrollableClientArea);
    }

    private void addCommandExecutorLine() {
        commandLine = new JTextField();
        commandLine.setPreferredSize(new Dimension(WINDOW_WIDTH, defaultFont.getSize() * 2));
        commandLine.setFont(defaultFont);
        commandLine.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String command = commandLine.getText();
                    executeCommandExecutor(command);
                    commandLine.setText("");
                }
            }
        });
        getContentPane().add(commandLine, BorderLayout.SOUTH);
    }

    private void executeCommandExecutor(String command) {
        String []parsedCommandExecutor = command.split(" ");

        String commandName = parsedCommandExecutor[0];
        String []params = new String[parsedCommandExecutor.length - 1];
        for (int i = 1; i < parsedCommandExecutor.length; i++) {
            params[i - 1] = parsedCommandExecutor[i];
        }

        commandExecutor.execute(commandName, params);
    }

    public void open() {
        commandLine.grabFocus();
        setVisible(true);
    }

    public void changeFile(String []initialData) {
        clientArea.setText("");
        for (String line: initialData) {
            clientArea.append(line + "\n");
        }
        clientArea.setEditable(true);
        commandLine.setEditable(false);
        clientArea.grabFocus();
    }
}
