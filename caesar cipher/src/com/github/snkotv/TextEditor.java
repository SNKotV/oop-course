package com.github.snkotv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends Window {

    private static int BUTTON_WIDTH = 148;
    private static int BUTTON_HEIGHT = 56;

    private File file;
    private JLabel pathLbl;
    private JTextField filePathField;
    private JTextArea textArea;
    private JScrollPane scrollPanel;
    private JButton saveButton;
    private JButton closeButton;
    private Font namesFont = new Font("Times New Roman", Font.ROMAN_BASELINE, 32);
    private Font textFont = new Font("Times New Roman", Font.ROMAN_BASELINE, 24);

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public TextEditor() {
        super("Editor", 800, 720);
        init();
    }

    public TextEditor(String filename) {
        this();
        file = new File(filename);
    }

    private void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int hgap = this.getWidth() / 64;
        int vgap = this.getHeight() / 48;
        setLayout(new FlowLayout(10, hgap, vgap));

        createFilePathPanel();
        getContentPane().add(pathLbl);
        getContentPane().add(filePathField);

        createTextInputArea();
        getContentPane().add(scrollPanel);

        createSaveButton();
        getContentPane().add(saveButton);

        createCloseButton();
        getContentPane().add(closeButton);
    }

    private void createFilePathPanel() {
        pathLbl = new JLabel("Path: ");
        pathLbl.setPreferredSize(new Dimension(this.getWidth() * 2 / 12, this.getHeight() / 14));
        pathLbl.setFont(namesFont);

        filePathField = new JTextField();
        filePathField.setPreferredSize(new Dimension(this.getWidth() * 9 / 12, this.getHeight() / 14));
        filePathField.setFont(namesFont);
    }

    private void createTextInputArea() {
        textArea = new JTextArea();
        textArea.setFont(textFont);

        scrollPanel = new JScrollPane(textArea);
        scrollPanel.setPreferredSize(new Dimension(this.getWidth() * 30 / 32, this.getHeight() * 10 / 14));
        scrollPanel.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    }

    private void createSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(TextEditor.BUTTON_WIDTH, TextEditor.BUTTON_HEIGHT));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    saveFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createCloseButton() {
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(TextEditor.BUTTON_WIDTH, TextEditor.BUTTON_HEIGHT));

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                filePathField.setText(null);
                textArea.setText(null);
                dispose();
            }
        });
    }

    public void readFile() throws IOException {
        filePathField.setText(file.getPath());

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = "";

        while ((line = br.readLine()) != null) {
            textArea.append(line + '\n');
        }

        br.close();
    }

    private void saveFile() throws IOException {
        file = new File(filePathField.getText());
        System.out.println(file.getPath());
        System.out.println(filePathField.getText());
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        String text = textArea.getText();
        bw.write(text);
        bw.close();
    }
}
