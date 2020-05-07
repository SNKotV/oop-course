package com.github.snkotv.filesystem;

import java.lang.module.FindException;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.util.LinkedList;

public class Folder extends FileSystemObject {

    public Folder(String name) {
        super(name);
        children = new LinkedList<>();
    }

    @Override
    public String[] read() throws Exception{
        throw new RuntimeException("Impossible to read data from folder");
    }

    @Override
    public void write(String[] data) throws Exception{
        throw new RuntimeException("Impossible to write data to folder");
    }

    @Override
    public void add(FileSystemObject fileSystemObject) throws Exception {
        children.add(fileSystemObject);
        fileSystemObject.setParent(this);
    }

    @Override
    public void remove(FileSystemObject fileSystemObject) throws Exception {
        for (FileSystemObject child: children) {
            if (child.equals(fileSystemObject)) {
                children.remove(child);
                child.setParent(null);
            } else {
                child.remove(fileSystemObject);
            }
        }
    }
}
