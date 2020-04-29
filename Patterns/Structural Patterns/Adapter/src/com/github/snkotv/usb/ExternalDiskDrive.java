package com.github.snkotv.usb;

import com.github.snkotv.disk.Disk;

public class ExternalDiskDrive implements USB {
    private boolean connected = false;
    private Disk disk;

    public void insertDisk(Disk disk) {
        this.disk = disk;
    }

    public boolean isDiskInserted() {
        return disk != null;
    }

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
        if (isDiskInserted()) {
            return disk.readData();
        } else {
            return "ERROR!!! There is no disk in disk drive";
        }
    }

    @Override
    public void writeData(String data) {
        if (isDiskInserted()) {
            disk.writeData(data);
        } else {
            System.out.println("There is no disk in disk drive");
        }
    }
}
