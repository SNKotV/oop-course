package com.github.snkotv.filesystem;

public class AudioFile extends File {
    public AudioFile(String name) {
        super(name);
        extension = "mp3";
    }
}
