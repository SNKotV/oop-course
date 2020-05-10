package com.github.snkotv.communication.chats;

import com.github.snkotv.communication.device.Communicator;

import java.util.LinkedList;

public class Account {
    private Chat chat;

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    private String userName;
    private String password;
    private Communicator usedDevice;

    public Account(String userName, String password, Communicator usedDevice) {
        this.userName = userName;
        this.password = password;
        this.usedDevice = usedDevice;
        messageHistory = new LinkedList<>();
    }

    public boolean equals(Account user) {
        return userName.equals(user.getUserName());
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Communicator getUsedDevice() {
        return usedDevice;
    }

    private int unreadMessages;

    public int getUnreadMessages() {
        return unreadMessages;
    }

    public void logout() {
        chat.logOut(this);
    }

    private LinkedList<String> messageHistory;

    public void clearMessageHistory() {
        messageHistory.clear();
        usedDevice.getChatScreen().displayMessages(messageHistory);
    }

    public void displayMessages() {
        unreadMessages = 0;
        usedDevice.getMainScreen().displayUnreadMessagesNumber(unreadMessages, chat);
        usedDevice.getChatScreen().displayMessages(messageHistory);
    }

    public void sendMessage(String message) {
        chat.sendNotification(message);
    }

    public void receiveNotification(String message) {
        messageHistory.add(message);
        if (usedDevice.getActiveScreen() == usedDevice.getMainScreen()) {
            unreadMessages++;
            usedDevice.getMainScreen().displayUnreadMessagesNumber(unreadMessages, chat);
        } else {
            usedDevice.getChatScreen().displayMessage(message);
        }
    }
}
