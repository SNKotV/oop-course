package com.github.snkotv.app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame {

    private Map<Integer, Student> groups = new HashMap<>();
    private static class StudentManagePanel extends JPanel{
        private int width;
        private int height;
        private Font textFont;
        private JLabel nameLbl;
        private JTextField nameTextField;
        private JLabel surnameLbl;
        private JTextField surnameTextField;
        private JLabel patronymicLbl;
        private JTextField patronymicTextField;
        private JLabel groupNumberLbl;
        private JComboBox groupNumberCb;
        private JLabel birthDateLbl;
        private JSpinner birthDateSpinner;
        private JButton addButton;
        private JButton deleteButton;

        private StudentManagePanel(Window master) {
            width = master.getWidth() * 95 / 100;
            height = master.getHeight() / 2;
            setPreferredSize(new Dimension(width, height));

            setLayout(new FlowLayout(0,20,10));

            textFont = new Font("Times New Roman", Font.ROMAN_BASELINE, height / 16);

            createNameField();
            add(nameLbl);
            add(nameTextField);

            createSurnameField();
            add(surnameLbl);
            add(surnameTextField);

            createPatronymicField();
            add(patronymicLbl);
            add(patronymicTextField);

            createGroupNumberField();
            add(groupNumberLbl);
            add(groupNumberCb);

            createBirthDateField();
            add(birthDateLbl);
            add(birthDateSpinner);

            Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Управление Студентом");
            setBorder(titledBorder);
        }

        private void createNameField() {
            nameLbl = new JLabel("Имя: ");
            nameLbl.setFont(textFont);

            nameTextField = new JTextField();
            nameTextField.setFont(textFont);
            nameTextField.setPreferredSize(new Dimension(width * 30 / 32, getTextHeight()));
        }

        private void createSurnameField() {
            surnameLbl = new JLabel("Фамилия: ");
            surnameLbl.setFont(textFont);

            surnameTextField = new JTextField();
            surnameTextField.setFont(textFont);
            surnameTextField.setPreferredSize(new Dimension(width * 30 / 32, getTextHeight()));
        }

        private void createPatronymicField() {
            patronymicLbl = new JLabel("Отчество: ");
            patronymicLbl.setFont(textFont);

            patronymicTextField = new JTextField();
            patronymicTextField.setFont(textFont);
            patronymicTextField.setPreferredSize(new Dimension(width * 30 / 32, getTextHeight()));
        }

        private void createGroupNumberField() {
            groupNumberLbl = new JLabel("№ группы: ");
            groupNumberLbl.setFont(textFont);

            groupNumberCb = new JComboBox();
            groupNumberCb.setFont(textFont);
            groupNumberCb.setPreferredSize(new Dimension(getTextHeight() * 3, getTextHeight()));
        }

        private void createBirthDateField() {
            birthDateLbl = new JLabel("Дата рождения: ");
            birthDateLbl.setFont(textFont);

            birthDateSpinner = new JSpinner();
            birthDateSpinner.setFont(textFont);
            birthDateSpinner.setPreferredSize(new Dimension(width / 3, getTextHeight()));
        }

        private int getTextHeight() {
            return height / 12;
        }
    }

    private static class GroupManagePanel extends JPanel {
        private int width;
        private int height;
        private JLabel groupNumberLbl;
        private JComboBox groupNumberCb;
        private Font textFont;
        private JButton toFile;
        private JButton toTable;

        private GroupManagePanel(Window master) {
            width = master.getWidth() * 95 / 100;
            height = master.getHeight() / 8;
            setPreferredSize(new Dimension(width, height));

            textFont = new Font("Times New Roman", Font.ROMAN_BASELINE, height / 4);

            createGroupNumberField();
            add(groupNumberLbl);
            add(groupNumberCb);

            Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Управление Группой");
            setBorder(titledBorder);
        }

        private void createGroupNumberField() {
            groupNumberLbl = new JLabel("№ группы: ");
            groupNumberLbl.setFont(textFont);

            groupNumberCb = new JComboBox();
            groupNumberCb.setFont(textFont);
            groupNumberCb.setPreferredSize(new Dimension(getTextHeight() * 3, getTextHeight()));
        }

        private int getTextHeight() {
            return height / 3;
        }
    }

    public Window(String title, int width, int height) {
        super(title);
        loadGroupsData();
        init(width,  height);
        setVisible(true);
    }

    private void init(int width, int height) {
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().add(new StudentManagePanel(this), BorderLayout.NORTH);
        getContentPane().add(new GroupManagePanel(this), BorderLayout.SOUTH);

        pack();
    }

    private void loadGroupsData() {

    }









}
