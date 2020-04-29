package com.github.snkotv;

import com.github.snkotv.usb.USB;

import java.util.HashMap;
import java.util.Map;

public class Laptop {
    private USB socket = null;
    private Map<String, String> memory;

    public Laptop() {memory = new HashMap<>(); }

    public void connectUSB(USB device) {
        if (device.isConnected()) {
            System.out.println("This device has been already connected to another computer");
        } else {
            if (socket != null) {
                System.out.println("There are no empty sockets for connecting this device");
            } else {
                device.connect();
                socket = device;
            }
        }
    }
    public void disconnectUSB() {
        socket.disconnect();
        socket = null;
    }

    public boolean isUSBConnected() {return socket != null; }

    public void createFile(String fileName) { memory.put(fileName, ""); }

    public void deleteFile(String fileName) {
        if (memory.containsKey(fileName)) {
            memory.remove(fileName);
        } else {
            System.out.println("There is no such file: " + fileName + " in this computer memory");
        }
    }

    public void writeToFile(String data, String fileName) {
        if (memory.containsKey(fileName)) {
            memory.put(fileName, memory.get(fileName) + data);
        } else {
            System.out.println("There is no such file: " + fileName + " in this computer memory");
        }
    }

    public void rewriteFile(String data, String fileName) {
        if (memory.containsKey(fileName)) {
            memory.put(fileName, data);
        } else {
            System.out.println("There is no such file: " + fileName + " in this computer memory");
        }
    }

    public void showAllFiles() {
        for (String fileName: memory.keySet()) {
            System.out.println(fileName);
        }
    }

    public void printFile(String fileName) {
        if (memory.containsKey(fileName)) {
            System.out.println(memory.get(fileName));
        } else {
            System.out.println("There is no such file: " + fileName + " in this computer memory");
        }
    }

    public void readDataFromConnectedDevice() {
        if (isUSBConnected()) {
            memory.put("data.usb", socket.readData());
        } else {
            System.out.println("There is no USB device connected to this computer");
        }
    }

    public void readDataFromConnectedDevice(String fileName) {
        if (isUSBConnected()) {
            if (memory.containsKey(fileName)) {
                rewriteFile(fileName, socket.readData());
            } else {
                createFile(fileName);
                writeToFile(fileName, socket.readData());
            }
        } else {
            System.out.println("There is no USB device connected to this computer");
        }
    }

    public void writeDataToConnectedDevice(String data) {
        if (isUSBConnected()) {
            socket.writeData(data);
        } else {
            System.out.println("There is no USB device connected to this computer");
        }
    }

    public void uploadFileToConnectedDevice(String fileName) {
        if (isUSBConnected()) {
            if (memory.containsKey(fileName)) {
                socket.writeData(memory.get(fileName));
            } else {
                System.out.println("There is no such file: " + fileName + " in this computer memory");
            }
        } else {
            System.out.println("There is no USB device connected to this computer");
        }
    }

    public void clearMemory() { memory.clear(); }
}
