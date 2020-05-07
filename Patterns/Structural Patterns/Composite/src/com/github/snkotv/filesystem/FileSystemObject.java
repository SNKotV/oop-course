package com.github.snkotv.filesystem;

import java.util.Collections;
import java.util.LinkedList;

public abstract class FileSystemObject {
    protected String name;
    protected FileSystemObject parent;
    protected LinkedList<FileSystemObject> children;

    protected FileSystemObject(String name) {
        this.name = name;
    }

    public boolean equals(FileSystemObject fileSystemObject) {
        return getFullName().equals(fileSystemObject.getFullName());
    }

    public String getName() {
        return name;
    }

    public FileSystemObject getParent() {
        return parent;
    }

    public void setParent(FileSystemObject fileSystemObject) {
        parent = fileSystemObject;
    }

    public LinkedList<FileSystemObject> getChildren() {
        return children;
    }

    public String getFullName() {
        LinkedList<String> nameParts = new LinkedList<>();
        FileSystemObject tmp = getParent();
        while (tmp != null) {
            nameParts.add(tmp.getName());
            tmp = tmp.getParent();
        }
        Collections.reverse(nameParts);
        String fullName = "";
        for (String part: nameParts) {
            fullName += part + '\\';
        }
        fullName += getName();
        return fullName;
    }

    public abstract String[] read() throws Exception;
    public abstract void write(String []data) throws Exception;
    public abstract void add(FileSystemObject child) throws Exception;
    public abstract void remove(FileSystemObject child) throws Exception;
}
