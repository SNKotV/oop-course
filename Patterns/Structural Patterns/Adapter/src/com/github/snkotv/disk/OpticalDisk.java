package com.github.snkotv.disk;

public class OpticalDisk implements Disk {
    private String memory;

    public OpticalDisk(String data) { memory = data; }

    @Override
    public String readData() {
        return memory;
    }

    @Override
    public void writeData(String data) {
        memory = data;
    }
}
