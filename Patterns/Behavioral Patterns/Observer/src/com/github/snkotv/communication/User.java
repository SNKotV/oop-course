package com.github.snkotv.communication;

import com.github.snkotv.communication.device.Communicator;

public class User {
    private String name;
    private String surname;

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

    private Communicator communicationDevice;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        communicationDevice = new Communicator(this);
    }

    public boolean isActive() {
        return communicationDevice.isActive();
    }

    public void switchOnCommunicationDevice() {
        communicationDevice.run();
    }

    public void switchOffCommunicationDevice() {
        communicationDevice.shutdown();
    }
}
