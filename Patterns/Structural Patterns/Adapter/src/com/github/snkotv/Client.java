package com.github.snkotv;

import com.github.snkotv.disk.OpticalDisk;
import com.github.snkotv.usb.ExternalDiskDrive;
import com.github.snkotv.usb.FlashCard;

public class Client {
    public static void main(String[] args) {
        Laptop acer = new Laptop();

        String textFile = "text.txt";
        String videoFile = "video.mp4";
        String audioFile = "audio.mp3";


        System.out.println("Create text file");
        acer.createFile(textFile);
        acer.writeToFile("Simple text", textFile);
        acer.printFile(textFile);
        System.out.println();

        System.out.println("Create video file");
        acer.createFile(videoFile);
        acer.writeToFile("Low quality video", videoFile);
        acer.printFile(videoFile);
        System.out.println();

        System.out.println("Rewrite video file");
        acer.rewriteFile("High quality video", videoFile);
        acer.printFile(videoFile);
        System.out.println();

        System.out.println("Create audio file");
        acer.createFile(audioFile);
        acer.writeToFile("Slipknot - \"All out life\"", audioFile);
        acer.printFile(audioFile);
        System.out.println();

        System.out.println("All files:");
        acer.showAllFiles();
        acer.deleteFile(textFile);
        System.out.println("\nText file deleted");
        acer.showAllFiles();
        System.out.println();

        FlashCard card = new FlashCard();

        System.out.println("Upload audio file to card");
        acer.uploadFileToConnectedDevice(audioFile);
        acer.connectUSB(card);
        System.out.println("\nConnect card and try again");
        acer.uploadFileToConnectedDevice(audioFile);
        System.out.println();

        Laptop asus = new Laptop();
        System.out.println("Try to download that file from card");
        asus.readDataFromConnectedDevice();
        System.out.println("\nTry to connect card");
        asus.connectUSB(card);
        acer.disconnectUSB();
        asus.connectUSB(card);
        asus.readDataFromConnectedDevice();
        System.out.println("\nConnect card again and download file\n");

        System.out.println("All files here are");
        asus.showAllFiles();
        System.out.println();

        System.out.println("data.usb contains");
        asus.printFile("data.usb");
        System.out.println();

        OpticalDisk cd = new OpticalDisk("Some information on disk");
        ExternalDiskDrive diskDrive = new ExternalDiskDrive();

        System.out.println("Clear memory and download info from disk");
        asus.clearMemory();
        asus.connectUSB(diskDrive);
        asus.disconnectUSB();
        asus.connectUSB(diskDrive);
        asus.readDataFromConnectedDevice();

        System.out.println("\nLook at file content");
        asus.printFile("data.usb");

        diskDrive.insertDisk(cd);
        asus.readDataFromConnectedDevice();
        System.out.println("\nTry again");
        asus.printFile("data.usb");
    }
}
