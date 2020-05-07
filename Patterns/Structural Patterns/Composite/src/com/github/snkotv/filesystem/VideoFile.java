package com.github.snkotv.filesystem;

public class VideoFile extends File {
    public VideoFile(String name) {
        super(name);
        extension = "mp4";
    }
}
