package com.github.snkotv.filesystem;

public abstract class File extends FileSystemObject {
    protected String extension;
    protected String[] data;

    public File(String name) {
        super(name);
        data = new String[0];
    }

    @Override
    public String getName() {
        return super.getName() + '.' + extension;
    }

    @Override
    public String[] read() throws Exception {
        return data.clone();
    }

    @Override
    public void write(String[] data) throws Exception {
        this.data = data.clone();
    }

    @Override
    public void add(FileSystemObject child) throws Exception {
        throw new RuntimeException("Can't add file or folder to file");
    }

    @Override
    public void remove(FileSystemObject child) throws Exception {
        throw new RuntimeException("Can't remove file or folder from file");
    }
}
