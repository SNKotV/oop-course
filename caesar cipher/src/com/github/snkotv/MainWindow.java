package com.github.snkotv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends Window {

    private static MainWindow instance = null;

    private ButtonPanel buttonPanel = new ButtonPanel();
    private OptionsPanel optionsPanel = new OptionsPanel();
    private TextEditor editor = new TextEditor();

    public MainWindow() {
        super("Caesar Cipher");
        init();
        setVisible(true);
    }

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private class ButtonPanel extends JPanel {
        private static final int BUTTON_WIDTH = 148;
        private static final int BUTTON_HEIGHT = 56;

        private JButton openInputFile;
        private JButton openOutputFile;
        private JButton encode;
        private JButton decode;
        private JButton exit;

        public ButtonPanel() {
            setPreferredSize(new Dimension(MainWindow.this.getWidth() / 2,MainWindow.this.getHeight()));
            setBorder(BorderFactory.createLoweredBevelBorder());
            int hgap = (MainWindow.this.getWidth() / 2 - BUTTON_WIDTH) / 2;
            int vgap = (MainWindow.this.getHeight() - 5 * BUTTON_HEIGHT) / 6;
            setLayout(new FlowLayout(0, hgap, vgap));

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
                    editor.setFile(new File(optionsPanel.inputFilePath.getText()));
                    try {
                        editor.readFile();
                        editor.setVisible(true);
                    } catch (IOException e) {
                        String msg = "Can't get access to file: \"" + optionsPanel.inputFilePath.getText() + "\".\n";
                        JOptionPane.showMessageDialog(null, msg);
                    }
                }
            });
        }

        private void openOutputFileButton() {
            openOutputFile = new JButton("Open output file");
            openOutputFile.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

            openOutputFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    editor.setFile(new File(optionsPanel.outputFilePath.getText()));
                    try {
                        editor.readFile();
                        editor.setVisible(true);
                    } catch (IOException e) {
                        String msg = "Can't get access to file: \"" + optionsPanel.outputFilePath.getText() + "\".\n";
                        JOptionPane.showMessageDialog(null, msg);
                    }
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
                        Encoder.setInput(new File(optionsPanel.inputFilePath.getText()));
                        Encoder.setOutput(new File(optionsPanel.outputFilePath.getText()));
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
                    try {
                        if(optionsPanel.keyword.getText().equals("")) {
                            throw new Exception();
                        }

                        Decoder.setKeyword(optionsPanel.keyword.getText());
                        Decoder.setInput(new File(optionsPanel.inputFilePath.getText()));
                        Decoder.setOutput(new File(optionsPanel.outputFilePath.getText()));
                        Decoder.decode();
                        JOptionPane.showMessageDialog(null, "Decoding is finished!");

                    }   catch (IOException e) {
                        String msg = "Can't get access to file: \"" + optionsPanel.inputFilePath.getText() + "\".\n";
                        JOptionPane.showMessageDialog(null, msg);
                    }   catch (Exception e) {
                        String msg = "Keyword field is empty.\n";
                        JOptionPane.showMessageDialog(null, msg);
                    }
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
        private static final int LABEL_HEIGHT = 32;
        private static final int TEXT_FIELD_WIDTH = 200;
        private static final int TEXT_FIELD_HEIGHT = 32;

        private JLabel shiftLbl;
        private JTextField shift;
        private JLabel inputFilePathLbl;
        private JTextField inputFilePath;
        private JLabel outputFilePathLbl;
        private JTextField outputFilePath;
        private JLabel keywordLbl;
        private JTextField keyword;

        private Font textFont = new Font("Times New Roman", Font.ROMAN_BASELINE, MainWindow.this.getHeight() / 14);

        public OptionsPanel() {
            setPreferredSize(new Dimension(MainWindow.this.getWidth() / 2,MainWindow.this.getHeight()));
            setBorder(BorderFactory.createLoweredBevelBorder());
            int hgap = (MainWindow.this.getWidth() / 2 - TEXT_FIELD_WIDTH) / 2;
            int vgap = (MainWindow.this.getHeight() - 8 * TEXT_FIELD_HEIGHT) / 9;
            setLayout(new FlowLayout(0, hgap, vgap));


            shiftField();
            add(shiftLbl);
            add(shift);

            inputFilePathField();
            add(inputFilePathLbl);
            add(inputFilePath);

            outputFilePathField();
            add(outputFilePathLbl);
            add(outputFilePath);

            keywordField();
            add(keywordLbl);
            add(keyword);
        }

        private void shiftField() {
            shiftLbl = new JLabel("Shift:");
            shiftLbl.setFont(textFont);
            shiftLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));


            shift = new JTextField();
            shift.setFont(textFont);
            shift.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        }

        private void inputFilePathField() {
            inputFilePathLbl = new JLabel("Input file path:");
            inputFilePathLbl.setFont(textFont);
            inputFilePathLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));


            inputFilePath = new JTextField();
            inputFilePath.setFont(textFont);
            inputFilePath.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        }

        private void outputFilePathField() {
            outputFilePathLbl = new JLabel("Output file path:");
            outputFilePathLbl.setFont(textFont);
            outputFilePathLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));

            outputFilePath = new JTextField();
            outputFilePath.setFont(textFont);
            outputFilePath.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        }

        private void keywordField() {
            keywordLbl = new JLabel("Keyword:");
            keywordLbl.setFont(textFont);
            keywordLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));

            keyword = new JTextField();
            keyword.setFont(textFont);
            keyword.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
        }

    }

    private void init() {
        setResizable(false);

        getContentPane().add(buttonPanel, BorderLayout.EAST);
        getContentPane().add(optionsPanel, BorderLayout.WEST);

        pack();
    }
}
