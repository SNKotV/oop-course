package com.github.snkotv.communication;

import java.awt.*;
import java.util.LinkedList;

public class CommunicationSystem {
    private static LinkedList<User> users = new LinkedList<>();

    public static void addUsers() {
        users.add(new User("Ivan", "Petrov"));
        users.add(new User("Petr", "Sidorov"));
        users.add(new User("Sidor", "Ivanov"));

        for (User user: users) {
            user.switchOnCommunicationDevice();
        }
    }

    public static boolean isActive() {
        for (User user: users) {
            if (user.isActive()) {
                return true;
            }
        }
        return false;
    }

    public static void communicate() {
        if (!isActive()) {
            return;
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> communicate());
    }

    public static void main(String[] args) throws InterruptedException {
        addUsers();
        communicate();
    }
}
