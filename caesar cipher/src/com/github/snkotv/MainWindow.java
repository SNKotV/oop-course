package com.github.snkotv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends Window {

    private static MainWindow instance = null;

    private MenuBar menuBar = new MenuBar();
    private ButtonPanel buttonPanel = new ButtonPanel();
    private OptionsPanel optionsPanel = new OptionsPanel();
    private String defaultPath = "";

    public MainWindow() {
        super("Caesar Cipher");
        init();
    }

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private class MenuBar extends JMenuBar {
        private JMenu mainMenu;
        private JMenu settingsMenu;

        public MenuBar() {
            mainMenu = new JMenu("Main");
            add(mainMenu);

            settingsMenu = new JMenu("Settings");
            add(settingsMenu);
        }
    }

    private class ButtonPanel extends JPanel {
        private static final int BUTTON_WIDTH = 128;
        private static final int BUTTON_HEIGHT = 32;

        private JButton openInputFile;
        private JButton openOutputFile;
        private JButton encode;
        private JButton decode;
        private JButton exit;

        public ButtonPanel() {
            setBackground(Color.RED); // delete

            setPreferredSize(new Dimension(MainWindow.this.getWidth() / 2,MainWindow.this.getHeight()));
            setLayout(new GridLayout(5, 1, 0, MainWindow.this.getHeight() / 16));

            openInputFileButton();
            add(openInputFile);

            openOutputFileButton();
            add(openOutputFile);

            encodeButton();
            add(encode);

            decodeButton();
            add(decode);

            exitButton();
            add(exit);
        }

        private void openInputFileButton() {
            openInputFile = new JButton("Open input file");
            openInputFile.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

            openInputFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {

                }
            });
        }

        private void openOutputFileButton() {
            openOutputFile = new JButton("Open output file");
            openOutputFile.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

            openOutputFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {

                }
            });
        }

        private void encodeButton() {
            encode = new JButton("Encode");
            encode.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

            encode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        Encoder.setShift(Integer.parseInt(optionsPanel.shift.getText()));
                        Encoder.setInput(new File(defaultPath + optionsPanel.inputFilePath.getText()));
                        Encoder.setOutput(new File(defaultPath + optionsPanel.outputFilePath.getText()));
                        Encoder.encode();
                        JOptionPane.showMessageDialog(null, "Encoding is finished!");

                    }   catch (NumberFormatException e) {
                        String msg = "Invalid value of shift field: \"" + optionsPanel.shift.getText() + "\".\n";
                        msg += "Integer value expected.";
                        JOptionPane.showMessageDialog(null, msg);

                    }   catch (IOException e) {
                        String msg = "Can't get access to file: \"" + optionsPanel.inputFilePath.getText() + "\".\n";
                        JOptionPane.showMessageDialog(null, msg);
                    }
                }
            });
        }

        private void decodeButton() {
            decode = new JButton("Decode");
            decode.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

            decode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {

                }
            });
        }

        private void exitButton() {
            exit = new JButton("Exit");
            exit.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    int option = JOptionPane.showConfirmDialog(MainWindow.this,"Are you sure?", "Exiting", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        }
    }

    private class OptionsPanel extends JPanel {
        private static final int LABEL_WIDTH = 200;
        private static final int LABEL_HEIGHT = 24;
        private static final int TEXT_FIELD_WIDTH = 200;
        private static final int TEXT_FIELD_HEIGHT = 24;

        private JLabel shiftLbl;
        private JTextField shift;
        private JLabel inputFilePathLbl;
        private JTextField inputFilePath;
        private JLabel outputFilePathLbl;
        private JTextField outputFilePath;
        private JLabel keywordLbl;
        private JTextField keyword;

        public OptionsPanel() {
            setBackground(Color.GRAY); // delete

            setPreferredSize(new Dimension(MainWindow.this.getWidth() / 2,MainWindow.this.getHeight()));


            Font textFont = new Font("Times New Roman", Font.ROMAN_BASELINE, MainWindow.this.getHeight() / 16);

            shiftLbl = new JLabel("Shift:");
            shiftLbl.setFont(textFont);
            shiftLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            add(shiftLbl);

            shift = new JTextField();
            shift.setFont(textFont);
            shift.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            add(shift);


            inputFilePathLbl = new JLabel("Input file path:");
            inputFilePathLbl.setFont(textFont);
            inputFilePathLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            add(inputFilePathLbl);

            inputFilePath = new JTextField();
            inputFilePath.setFont(textFont);
            inputFilePath.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            add(inputFilePath);

            outputFilePathLbl = new JLabel("Output file path:");
            outputFilePathLbl.setFont(textFont);
            outputFilePathLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            add(outputFilePathLbl);

            outputFilePath = new JTextField();
            outputFilePath.setFont(textFont);
            outputFilePath.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            add(outputFilePath);

            keywordLbl = new JLabel("Keyword:");
            keywordLbl.setFont(textFont);
            keywordLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
            add(keywordLbl);

            keyword = new JTextField();
            keyword.setFont(textFont);
            keyword.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
            add(keyword);

        }
    }

    private void init() {
        setResizable(false);

        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
        getContentPane().add(optionsPanel, BorderLayout.WEST);

        pack();
    }
}
