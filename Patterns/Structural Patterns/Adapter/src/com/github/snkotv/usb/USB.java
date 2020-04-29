package com.github.snkotv.usb;

public interface USB {
    void connect();
    void disconnect();
    boolean isConnected();
    String readData();
    void writeData(String data);
}
