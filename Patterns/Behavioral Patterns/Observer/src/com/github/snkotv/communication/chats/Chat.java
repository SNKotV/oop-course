package com.github.snkotv.communication.chats;

import com.sun.jdi.connect.Connector;

import java.util.LinkedList;

public abstract class Chat {
    protected String name;

    public String getName() {
        return name;
    }

    protected LinkedList<Account> registeredUsers;
    protected LinkedList<Account> activeUsers;

    protected Chat() {
        registeredUsers = new LinkedList<>();
        activeUsers = new LinkedList<>();
    }

    public void addUser(Account user) {
        registeredUsers.add(user);
        String message = "";
        sendNotification(message);
    }

    public void removeUser(Account user) {
        registeredUsers.remove(user);
        activeUsers.remove(user);
    }

    private void sendNotification(String message) {
        for (Account user: registeredUsers) {
            user.receiveNotification(message);
        }
    }
}
