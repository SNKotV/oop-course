package com.github.snkotv.filesystem;

import java.nio.file.NoSuchFileException;
import java.util.LinkedList;
import java.util.Queue;

public class FileSystem {
    private static final LinkedList<String> availableExtensions = new LinkedList<>() {
        {
         add("txt");
         add("png");
         add("mp3");
         add("mp4");
        }
    };

    public static LinkedList<String> getAvailableExtensions() {
        return availableExtensions;
    }

    private static FileSystem instance = null;
    private FileSystemObject root;
    private FileSystemObject currentDirectoryPointer;

    private FileSystem() {
        root = new Folder("@root@");
        currentDirectoryPointer = root;
    }

    public static FileSystem getInstance() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }

    public FileSystemObject getRootPointer() { return root; }
    public FileSystemObject getCurrentDirectoryPointer() { return currentDirectoryPointer; }

    public void resetRootPointer() { currentDirectoryPointer = root; }

    public void moveDirectoryPointer(String newDirectoryName) {
        Queue<FileSystemObject> directoriesQueue = new LinkedList<>();
        directoriesQueue.add(root);

        while (!directoriesQueue.isEmpty()) {
            FileSystemObject current = directoriesQueue.poll();
            if (current.getFullName().equals(newDirectoryName)) {
                currentDirectoryPointer = current;
                break;
            }
            if (current.getChildren() != null) {
                for (FileSystemObject fileSystemObject: current.getChildren()) {
                    directoriesQueue.add(fileSystemObject);
                }
            }
        }
    }


    public void createNewFolder(String folderName) throws Exception {
        try {
            getFileSystemObjectByName(currentDirectoryPointer.getFullName() + "\\" + folderName);
            throw new IllegalArgumentException("Can't create two directories with identical names");
        } catch (NoSuchFileException e) {
            FileSystemObject folder = new Folder(folderName);
            getCurrentDirectoryPointer().add(folder);
        }
    }

    public void deleteFolder(String folderName) throws Exception {
        root.remove(getFileSystemObjectByName(folderName));
    }

    private FileSystemObject getFileSystemObjectByName(String name) throws Exception{
        Queue<FileSystemObject> directoriesQueue = new LinkedList<>();
        directoriesQueue.add(root);

        while (!directoriesQueue.isEmpty()) {
            FileSystemObject current = directoriesQueue.poll();
            if (current.getFullName().equals(name)) {
                return current;
            }
            if (current.getChildren() != null) {
                for (FileSystemObject fileSystemObject: current.getChildren()) {
                    directoriesQueue.add(fileSystemObject);
                }
            }
        }
        throw new NoSuchFileException("Can't find such file system object");
    }

    public void createFile(String name, String extension) throws Exception {
        try {
            getFileSystemObjectByName(currentDirectoryPointer.getFullName() + "\\" + name + "." + extension);
            throw new IllegalArgumentException("Can't create two directories with identical names");
        } catch (NoSuchFileException e) {
            FileSystemObject file = null;
            switch (extension) {
                case "txt":
                    file = new TextFile(name);
                    break;
                case "png":
                    file = new ImageFile(name);
                    break;
                case "mp3":
                    file = new AudioFile(name);
                    break;
                case "mp4":
                    file = new VideoFile(name);
                    break;
            }
            getCurrentDirectoryPointer().add(file);
        }
    }

    public void removeFile(String fileName) throws Exception {
        root.remove(getFileSystemObjectByName(fileName));
    }

    public String[] readFile(String fileName) throws Exception {
        return getFileSystemObjectByName(fileName).read();
    }

    public void writeFile(String fileName, String []data) throws Exception {
        getFileSystemObjectByName(fileName).write(data);
    }

}
