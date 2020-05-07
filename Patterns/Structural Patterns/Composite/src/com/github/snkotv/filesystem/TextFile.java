package com.github.snkotv.filesystem;

public class TextFile extends File {

    public TextFile(String name) {
        super(name);
        this.extension = "txt";
    }
}
