package com.github.snkotv.communication.chats;

import com.github.snkotv.communication.device.Communicator;
import com.github.snkotv.communication.exceptions.InvalidPasswordException;
import com.github.snkotv.communication.exceptions.InvalidUserNameException;

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

    public boolean isConnected(Communicator device) {
        for (Account user: activeUsers) {
            if (user.getUsedDevice().equals(device)) {
                return true;
            }
        }
        return false;
    }

    public void display(Communicator device) {
        for (Account user: activeUsers) {
            if (user.getUsedDevice().equals(device)) {
                user.displayMessages();
            }
        }
    }

    public void addUser(Account user) throws InvalidUserNameException {
        for (Account registeredUser: registeredUsers) {
            if (registeredUser.getUserName().equals(user.getUserName())) {
                throw  new InvalidUserNameException("This username isn't free");
            }
        }
        user.setChat(this);
        registeredUsers.add(user);
        String message = user.getUserName() + " joins to conversation";
        sendNotification(message);
    }

    public void removeUser(Account user) throws InvalidPasswordException, InvalidUserNameException {
        for (Account activeUser: activeUsers) {
            if (activeUser.equals(user)) {
                throw  new InvalidUserNameException("Logged in from another device");
            }
        }

        for (Account registeredUser: registeredUsers) {
            if (registeredUser.equals(user)) {
                if (registeredUser.getPassword().equals(user.getPassword())) {
                    registeredUsers.remove(registeredUser);
                    activeUsers.remove(registeredUser);
                    String message = user.getUserName() + " leaves this conversation";
                    sendNotification(message);
                    return;
                } else {
                    throw  new InvalidPasswordException("Invalid password");
                }
            }
        }
        throw new InvalidUserNameException("No registered users with such name");
    }

    public void logIn(Account user) throws InvalidUserNameException, InvalidPasswordException {
        for (Account activeUser: activeUsers) {
            if (activeUser.equals(user)) {
                throw  new InvalidUserNameException("Logged in from another device");
            }
        }

        for (Account registeredUser: registeredUsers) {
            if (registeredUser.equals(user)) {
                if (registeredUser.getPassword().equals(user.getPassword())) {
                    activeUsers.add(registeredUser);
                    registeredUser.setUsedDevice(user.getUsedDevice());
                    registeredUser.getUsedDevice().getChatScreen().setChatTitle(name);
                    registeredUser.getUsedDevice().getChatScreen().setNickName(registeredUser.getUserName());
                    registeredUser.getUsedDevice().getChatScreen().setCurrentUser(registeredUser);
                    registeredUser.getUsedDevice().enterChat(this);

                    String message = user.getUserName() + " log in to chat";
                    sendNotification(message);
                    return;
                } else {
                    throw  new InvalidPasswordException("Invalid password");
                }
            }
        }
        throw new InvalidUserNameException("No registered users with such name");
    }

    public void logOut(Account user) {
        for (Account activeUser: activeUsers) {
            if (activeUser.equals(user)) {
                activeUsers.remove(activeUser);

                String message = activeUser.getUserName() + " log out from chat";
                sendNotification(message);
                return;
            }
        }
    }

    public void sendNotification(String message) {
        for (Account user: activeUsers) {
            user.receiveNotification(message);
        }
    }
}
