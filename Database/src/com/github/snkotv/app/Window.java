package com.github.snkotv.app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame {

    private Map<Integer, Student> groups = new HashMap<>();
    private JPanel dialogPanel;

    private static class StudentManagePanel extends JPanel{
        private Window master;
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
            this.master = master;
            width = master.getWidth() * 95 / 200;
            height = master.getHeight() / 2;
            setPreferredSize(new Dimension(width, height + 30));

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

            createAddButton();
            add(addButton);

            createDeleteButton();
            add(deleteButton);

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

        private void createAddButton() {
            addButton = new JButton("Добавить");
            addButton.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));
        }

        private void createDeleteButton() {
            deleteButton = new JButton("Исключить");
            deleteButton.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));
        }

        private int getTextHeight() {
            return height / 12;
        }
    }

    private static class GroupManagePanel extends JPanel {
        private Window master;
        private int width;
        private int height;
        private JLabel groupNumberLbl;
        private JComboBox groupNumberCb;
        private Font textFont;
        private JButton toFile;
        private JButton toTable;

        private GroupManagePanel(Window master) {
            this.master = master;

            width = master.getWidth() * 95 / 200;
            height = master.getHeight() / 8;
            setPreferredSize(new Dimension(width, height + 20));

            setLayout(new FlowLayout(0,40,10));

            textFont = new Font("Times New Roman", Font.ROMAN_BASELINE, height / 4);

            createGroupNumberField();
            add(groupNumberLbl);
            add(groupNumberCb);

            createToFileButton();
            add(toFile);

            createToTableButton();
            add(toTable);

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

        private void createToFileButton() {
            toFile = new JButton("В файл");
            toFile.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));
        }

        private void createToTableButton() {
            toTable = new JButton("В таблицу");
            toTable.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));

            toTable.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    master.setSize(master.getWidth() * 2, master.getHeight());
                }
            });
        }

        private int getTextHeight() {
            return height / 3;
        }
    }

    private static class Table extends JPanel {
        private Window master;
        private int width;
        private int height;
        private String[] header = new String[] {"id", "Фамилия", "Имя", "Отчество", "Номер группы", "Дата рождения"};
        private DefaultTableModel model;
        private JTable table;
        private JScrollPane scrollPane;

        public Table(Window master) {
            this.master = master;
            width = master.getWidth() * 95 / 200;
            height = master.getHeight() * 6 / 8;
            setPreferredSize(new Dimension(width, height));

            createTable();
            add(scrollPane);
        }

        private void createTable() {
            model = new DefaultTableModel(header, 0);

            table = new JTable(model);

            for (int i = 0; i < header.length; i++) {
                TableColumn col = table.getTableHeader().getColumnModel().getColumn(i);
                col.setHeaderValue(header[i]);
                table.getTableHeader().resizeAndRepaint();
            }

            scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(width - 5, height - 5));
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

        dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.add(new StudentManagePanel(this), BorderLayout.NORTH);
        dialogPanel.add(new GroupManagePanel(this), BorderLayout.SOUTH);

        getContentPane().add(dialogPanel, BorderLayout.WEST);
        getContentPane().add(new Table(this), BorderLayout.EAST);

        pack();
    }

    private void loadGroupsData() {

    }

//    private void createTable() {
//        String[] header = new String[] {"id", "Фамилия", "Имя", "Отчество", "Номер группы", "Дата рождения"};
//
//        int width = getWidth() * 95 / 200;
//        int height = dialogPanel.getHeight();
//
//        DefaultTableModel model = new DefaultTableModel(header, 0);
//
//        table = new JTable(model);
//        table.setPreferredSize(new Dimension(width, height));
//
//
//        for (int i = 0; i < header.length; i++) {
//            TableColumn col = table.getTableHeader().getColumnModel().getColumn(i);
//            col.setHeaderValue(header[i]);
//            table.getTableHeader().resizeAndRepaint();
//        }
//
//    }









}
