package com.github.snkotv.app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Window extends JFrame {

    private ArrayList<Integer> groupNumbers;
    private Map<Integer, LinkedList<Student>> groups = new HashMap<>();
    private JPanel dialogPanel;
    private Table table;
    private StudentManagePanel studentManagePanel;
    private GroupManagePanel groupManagePanel;

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
        private SpinnerDateModel spinnerModel;
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
            for (Integer number: master.groupNumbers) {
                groupNumberCb.addItem(number);
            }
        }

        private void createBirthDateField() {
            birthDateLbl = new JLabel("Дата рождения: ");
            birthDateLbl.setFont(textFont);

            spinnerModel = new SpinnerDateModel();
            birthDateSpinner = new JSpinner(spinnerModel);
            birthDateSpinner.setFont(textFont);
            birthDateSpinner.setPreferredSize(new Dimension(width / 3, getTextHeight()));

            JSpinner.DateEditor editor = new JSpinner.DateEditor(birthDateSpinner, "yyyy.MM.dd");
            Calendar calendar = new GregorianCalendar(2000, Calendar.JANUARY, 1);
            birthDateSpinner.setValue(calendar.getTime());
            birthDateSpinner.setEditor(editor);

            DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
            formatter.setAllowsInvalid(false);
            formatter.setOverwriteMode(true);
        }

        private void createAddButton() {
            addButton = new JButton("Добавить");
            addButton.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText().trim();
                    String surname = surnameTextField.getText().trim();
                    String patronymic = patronymicTextField.getText().trim();
                    Integer groupNumber = Integer.parseInt(groupNumberCb.getSelectedItem().toString());
                    Date birthDate = (Date) birthDateSpinner.getValue();

                    if (name.equals("")) {
                        String msg = "Необходимо заполнить поле \"Имя\".\n";
                        JOptionPane.showMessageDialog(null, msg);
                    } else if (surname.equals("")) {
                        String msg = "Необходимо заполнить поле \"Фамилия\"." + "\n";
                        JOptionPane.showMessageDialog(null, msg);
                    } else {
                        Student student = null;
                        boolean founded = false;
                        for (Student stud: master.groups.get(groupNumber)) {
                            if (stud.equals(name, surname, patronymic, birthDate)) {
                                founded = true;
                                student = stud;
                                break;
                            }
                        }

                        if (founded) {
                            String msg = "Студент " + student + " уже находится в " + groupNumber +" группе.\n";
                            JOptionPane.showMessageDialog(null, msg);
                            return;
                        }

                        student = new Student(name, surname, patronymic, birthDate);
                        master.groups.get(groupNumber).add(student);
                        String msg = "Студент " + student + " был успешно зачислен в " + groupNumber +" группу.\n";
                        JOptionPane.showMessageDialog(null, msg);
                    }
                }
            });
        }

        private void createDeleteButton() {
            deleteButton = new JButton("Исключить");
            deleteButton.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText().trim();
                    String surname = surnameTextField.getText().trim();
                    String patronymic = patronymicTextField.getText().trim();
                    Integer groupNumber = Integer.parseInt(groupNumberCb.getSelectedItem().toString());
                    Date birthDate = (Date) birthDateSpinner.getValue();

                    if (name.equals("")) {
                        String msg = "Необходимо заполнить поле \"Имя\".\n";
                        JOptionPane.showMessageDialog(null, msg);
                    } else if (surname.equals("")) {
                        String msg = "Необходимо заполнить поле \"Фамилия\"." + "\n";
                        JOptionPane.showMessageDialog(null, msg);
                    } else {
                        Student student = null;
                        boolean deleted = false;

                        for (Student stud: master.groups.get(groupNumber)) {
                            if (stud.equals(name, surname, patronymic, birthDate)) {
                                deleted = true;
                                student = stud;
                                master.groups.get(groupNumber).remove(stud);
                                break;
                            }
                        }

                        if (deleted) {
                            String msg = "Студент " + student + " был успешно исключен из " + groupNumber +" группы.\n";
                            JOptionPane.showMessageDialog(null, msg);
                        } else {
                            String msg = "Студент " + surname + " " + name + " " + patronymic + " не найден в списке " + groupNumber +" группы.\n";
                            JOptionPane.showMessageDialog(null, msg);
                        }
                    }
                }
            });
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
            groupNumberCb.addItem("Все");
            for (Integer number: master.groupNumbers) {
                groupNumberCb.addItem(number);
            }
        }

        private void createToFileButton() {
            toFile = new JButton("В файл");
            toFile.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));

            toFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String cbValue = groupNumberCb.getSelectedItem().toString();

                    if (cbValue.equals("Все")) {
                        boolean success = true;

                        for (Integer number: master.groupNumbers) {
                            String fileName = "" + number + ".group";
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter("files/" + fileName));
                                bw.write("");

                                for (Student student: master.groups.get(number)) {
                                    String line = student.getId() + ":" +
                                            student.getName() + ":" +
                                            student.getSurname() + ":" +
                                            student.getPatronymic() + ":" +
                                            student.getStringBirthDate() + "\n";
                                    bw.append(line);
                                }

                                bw.close();

                            } catch (IOException ex) {
                                success = false;
                                String msg = "Не удалось записать данные в файл" + fileName + ".\n";
                                JOptionPane.showMessageDialog(null, msg);
                            }
                        }
                        if (success) {
                            String msg = "Запись данных в файл была проведена успешно.\n";
                            JOptionPane.showMessageDialog(null, msg);
                        }

                    } else {
                        String fileName = "" + Integer.parseInt(cbValue) + ".group";
                        boolean success = true;
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("files/" + fileName));
                            bw.write("");

                            for (Student student: master.groups.get(Integer.parseInt(cbValue))) {
                                String line = student.getId() + ":" +
                                        student.getName() + ":" +
                                        student.getSurname() + ":" +
                                        student.getPatronymic() + ":" +
                                        student.getStringBirthDate() + "\n";
                                bw.append(line);
                            }

                            bw.close();

                        } catch (IOException ex) {
                            success = false;
                            String msg = "Не удалось записать данные в файл" + fileName + ".\n";
                            JOptionPane.showMessageDialog(null, msg);
                        }

                        if (success) {
                            String msg = "Запись данных в файл была проведена успешно.\n";
                            JOptionPane.showMessageDialog(null, msg);
                        }
                    }

                }
            });
        }

        private void createToTableButton() {
            toTable = new JButton("В таблицу");
            toTable.setPreferredSize(new Dimension(getTextHeight() * 5, getTextHeight() * 2));

            toTable.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    master.table.clear();

                    String cbValue = groupNumberCb.getSelectedItem().toString();
                    if (cbValue.equals("Все")) {
                        for (Integer number: master.groupNumbers) {
                            for (Student student: master.groups.get(number)) {
                                Object[] data = new Object[] {student.getId(),
                                        student.getName(), student.getSurname(), student.getPatronymic(),
                                        number, student.getStringBirthDate()};
                                master.table.addRow(data);
                            }
                        }
                    } else {
                        for (Student student: master.groups.get(Integer.parseInt(cbValue))) {
                            Object[] data = new Object[] {student.getId(),
                                    student.getName(), student.getSurname(), student.getPatronymic(),
                                    cbValue, student.getStringBirthDate()};
                            master.table.addRow(data);
                        }
                    }
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
        private String[] header = new String[] {"id", "Имя", "Фамилия", "Отчество", "Номер группы", "Дата рождения"};
        private DefaultTableModel model;
        private JTable table;
        private JScrollPane scrollPane;
        private boolean idReverse = false;
        private boolean surnameReverse = false;
        private boolean birthDateReverse = false;

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
            table.setEnabled(false);

            for (int i = 0; i < header.length; i++) {
                TableColumn col = table.getTableHeader().getColumnModel().getColumn(i);
                col.setHeaderValue(header[i]);
            }
            table.getTableHeader().setReorderingAllowed(false);
            table.setAutoCreateRowSorter(true);


            scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(width - 5, height - 5));

            table.getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = table.columnAtPoint(e.getPoint());
                    String cbValue = master.groupManagePanel.groupNumberCb.getSelectedItem().toString();

                    Comparator<Student> idComp = Student::compareById;
                    Comparator<Student> surnameComp = Student::compareBySurname;
                    Comparator<Student> bdateComp = Student::compareByBirthDate;

                    if (col == 0) {

                        if (cbValue.equals("Все")) {
                            for (Integer number: master.groupNumbers) {
                                if (idReverse) {
                                    master.groups.get(number).sort(idComp.reversed());
                                } else {
                                    master.groups.get(number).sort(idComp);
                                }
                            }
                        } else {
                            if (idReverse) {
                                master.groups.get(Integer.parseInt(cbValue)).sort(idComp.reversed());
                            } else {
                                master.groups.get(Integer.parseInt(cbValue)).sort(idComp);
                            }
                        }

                        if (idReverse)
                            idReverse = false;
                        else
                            idReverse = true;

                    } else if (col == 2) {

                        if (cbValue.equals("Все")) {
                            for (Integer number: master.groupNumbers) {
                                if (surnameReverse) {
                                    master.groups.get(number).sort(surnameComp.reversed());
                                } else {
                                    master.groups.get(number).sort(surnameComp);
                                }
                            }
                        } else {
                            if (surnameReverse) {
                                master.groups.get(Integer.parseInt(cbValue)).sort(surnameComp.reversed());
                            } else {
                                master.groups.get(Integer.parseInt(cbValue)).sort(surnameComp);
                            }
                        }

                        if (surnameReverse)
                            surnameReverse = false;
                        else
                            surnameReverse = true;

                    } else if (col == 5) {

                        if (cbValue.equals("Все")) {
                            for (Integer number: master.groupNumbers) {
                                if (birthDateReverse) {
                                    master.groups.get(number).sort(bdateComp.reversed());
                                } else {
                                    master.groups.get(number).sort(bdateComp);
                                }
                            }
                        } else {
                            if (birthDateReverse) {
                                master.groups.get(Integer.parseInt(cbValue)).sort(bdateComp.reversed());
                            } else {
                                master.groups.get(Integer.parseInt(cbValue)).sort(bdateComp);
                            }
                        }

                        if (birthDateReverse)
                            birthDateReverse = false;
                        else
                            birthDateReverse = true;
                    }
                }
            });
        }

        public void addRow(Object[] data) {
            model = (DefaultTableModel) table.getModel();
            model.addRow(data);
        }

        public void clear() {
            model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
        }

    }

    public Window(String title, int width, int height) {
        super(title);
        try {
            loadGroupsData();
        }
        catch (IOException e) {
            String msg = "Невозможно получить доступ к файлу groupNumbers.init.\n" +
                    "Возможно данный файл был поврежден. Работа программы будет остановлена";
            JOptionPane.showMessageDialog(null, msg);
            System.exit(-1);
        }
        init(width,  height);
        setVisible(true);
    }

    private void init(int width, int height) {
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        studentManagePanel = new StudentManagePanel(this);
        groupManagePanel = new GroupManagePanel(this);

        dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.add(studentManagePanel, BorderLayout.NORTH);
        dialogPanel.add(groupManagePanel, BorderLayout.SOUTH);

        table = new Table(this);

        getContentPane().add(dialogPanel, BorderLayout.WEST);
        getContentPane().add(table, BorderLayout.EAST);

        pack();
    }

    private void loadGroupsData() throws IOException {
        groupNumbers = new ArrayList<>();

        File initFile = new File("files/groupNumbers.init");
        BufferedReader br = new BufferedReader(new FileReader(initFile));
        String line = "";

        while ((line = br.readLine()) != null) {
            try {
                groupNumbers.add(Integer.parseInt(line));
            }
            catch (NumberFormatException e) {
                String msg = "Невозможно корректно извлечь информацию из файла groupNumbers.init.\n" +
                        "Возможно данный файл был поврежден. Работа программы будет остановлена";
                JOptionPane.showMessageDialog(null, msg);
                System.exit(-1);
            }
        }

        for (Integer number: groupNumbers) {
            groups.put(number, new LinkedList<>());
        }

        BufferedWriter bw;
        String fileName = "";
        for (Integer number: groupNumbers) {
            fileName = "" + number + ".group";
            try {
                br = new BufferedReader(new FileReader("files/" + fileName));

                while ((line = br.readLine()) != null) {
                    if (line.equals(""))
                        continue;
                    try {
                        Student student = Student.parseStudent(line);
                        groups.get(number).add(student);
                    } catch (Exception e) {
                        String msg = "Данные файла " + fileName +"были повреждены.\n Файл будет полностью очищен.";
                        JOptionPane.showMessageDialog(null, msg,"Внимание" ,JOptionPane.WARNING_MESSAGE);
                        groups.get(number).clear();
                        bw = new BufferedWriter(new FileWriter("files/" + fileName));
                        bw.write("");
                        bw.close();
                        break;
                    }
                }

            } catch (Exception e) {
                bw = new BufferedWriter(new FileWriter("files/" + fileName));
                bw.write("");
                bw.close();
            }
        }

        br.close();
    }
}
