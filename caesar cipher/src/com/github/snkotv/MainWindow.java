package com.github.snkotv;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends Window {

    private static MainWindow instance = null;

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
        private static final int BUTTON_WIDTH = 10;
        private static final int BUTTON_HEIGHT = 10;

        private JButton openInputFile;
        private JButton openOutputFile;
        private JButton encode;
        private JButton decode;
        private JButton exit;

        public ButtonPanel() {
            setBackground(Color.RED); // delete

            setPreferredSize(new Dimension(MainWindow.this.getWidth() / 2,MainWindow.this.getHeight()));
            setLayout(new GridLayout(5, 1, 0, MainWindow.this.getHeight() / 16));

            openInputFile = new JButton("Open input file");
            openInputFile.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            add(openInputFile);

            openOutputFile = new JButton("Open output file");
            openOutputFile.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            add(openOutputFile);

            encode = new JButton("Encode");
            encode.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            add(encode);

            decode = new JButton("Decode");
            decode.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            add(decode);

            exit = new JButton("Exit");
            exit.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            add(exit);

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

    private MenuBar menuBar = new MenuBar();
    private ButtonPanel buttonPanel = new ButtonPanel();
    private OptionsPanel optionsPanel = new OptionsPanel();


    private void init() {
        setResizable(false);

        getContentPane().add(menuBar, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
        getContentPane().add(optionsPanel, BorderLayout.WEST);

       pack();
    }


}
