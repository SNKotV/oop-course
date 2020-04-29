package com.github.snkotv.usb;

public class FlashCard implements USB {
    private boolean connected = false;
    private String memory = "";

    @Override
    public void connect() {
        connected = true;
    }

    @Override
    public void disconnect() {
        connected = false;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public String readData() {
        return memory;
    }

    @Override
    public void writeData(String data) {
        memory = data;
    }
}
