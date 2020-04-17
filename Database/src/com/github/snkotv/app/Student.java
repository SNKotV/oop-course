package com.github.snkotv.app;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Student implements Comparator {
    private static int availableID = 1;

    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;

    public Student(String name, String surname, String patronymic, Date birthDate) {
        id = availableID++;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
    }

    public Student(String name, String surname, Date birthDate) {
        this(name, surname, "", birthDate);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getStringBirthDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean equals(String name, String surname, String patronymic, Date birthDate) {
        return this.name.equals(name) && this.surname.equals(surname)
                && this.patronymic.equals(patronymic) && this.birthDate.equals(birthDate);
    }

    public static Student parseStudent(String str) throws Exception {
        String[] parts = str.split(":");

        String name = parts[1];
        String surname = parts[2];
        String patronymic = parts[3];

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        Date birthDate = sdf.parse(parts[4]);

        Student student = new Student(name, surname, patronymic, birthDate);

        return student;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic;
    }

    public int compareById(Student st) {
        if (id < st.id) {
            return -1;
        } else if (id == st.id) {
            return 0;
        }
        return 1;
    }

    public int compareBySurname(Student st) {
        return surname.compareTo(st.name);
    }

    public int compareByBirthDate(Student st) {
       return birthDate.compareTo(st.birthDate);
    }

    @Override
    public int compare(Object o, Object t1) {
        return 0;
    }
}
