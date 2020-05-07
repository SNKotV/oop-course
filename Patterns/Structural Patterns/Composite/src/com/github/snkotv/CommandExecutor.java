package com.github.snkotv;

import com.github.snkotv.filesystem.File;
import com.github.snkotv.filesystem.FileSystem;
import com.github.snkotv.filesystem.FileSystemObject;

import java.nio.file.NoSuchFileException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private Terminal terminalInstance;
    private String []state;
    private String openedFileName = null;
    private String []params;

    public CommandExecutor(Terminal instance) {
        terminalInstance = instance;
    }

    private Map<String, Runnable> commandList = Collections.unmodifiableMap(new HashMap<>() {
        {
            put("exit", () -> quit());
            put("clear", () -> cls());
            put("mkf", () -> mkf());
            put("rmf", () -> rmf());
            put("open", () -> open());
            put("close", () -> close());
            put("mkdir", () -> mkdir());
            put("rmdir", () -> rmdir());
            put("cd", () -> cd());
            put("look", () -> look());
            put("tree", () -> tree(FileSystem.getInstance().getRootPointer(), 0));
            put("help", () -> help());
        }
    });

    public void execute(String commandName, String[] parameters) {
        params = parameters;
        if (state == null || commandName.equals("close")) {
            if (!commandName.equals("close")) {
                printCurrentCommand(commandName);
            }
            if (commandList.containsKey(commandName)) {
                commandList.get(commandName).run();
            } else {
                terminalInstance.getClientArea().append("command: " + commandName + " not found, type help to get help\n");
            }
            if (!commandName.equals("open")) {
                printCurrentDirectory();
            }
        }
    }

    private void printCurrentCommand(String commandName) {
        terminalInstance.getClientArea().append(commandName + ' ');
        for (String param: params) {
            terminalInstance.getClientArea().append(param + ' ');
        }
    }

    private void printCurrentDirectory() {
        terminalInstance.getClientArea()
                .append(FileSystem.getInstance().getCurrentDirectoryPointer().getFullName() + "> ");
    }

    private void quit() {
        terminalInstance.dispose();
        System.exit(0);
    }

    private void cls() {
        if (params.length > 0) {
            terminalInstance.getClientArea().append("command: clear doesn't take any parameters\n");
        } else {
            terminalInstance.getClientArea().setText("");
        }
    }

    private void mkf() {
        if (params.length != 1) {
            terminalInstance.getClientArea().append("invalid number of parameters, " +
                    "command: mkf should take 1 parameter (name of file with extension)\n");
        } else {
            if (!params[0].contains(".")) {
                terminalInstance.getClientArea().append("invalid type of parameter, " +
                        "command: mkf should take 1 parameter (name of file with extension)\n");
            } else {
                String []parts = params[0].split("\\.");
                String name = "";
                for (int i = 0; i < parts.length - 1; i++) {
                    name += parts[i];
                }
                String extension = parts[parts.length - 1];

                if (FileSystem.getInstance().getAvailableExtensions().contains(extension)) {
                    try {
                        FileSystem.getInstance().createFile(name, extension);
                        terminalInstance.getClientArea().append("\n");
                    } catch (IllegalArgumentException e) {
                        terminalInstance.getClientArea().append("file with the same name has been already created here\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    terminalInstance.getClientArea().append("invalid file extension " +
                            "(available extensions: txt, png, mp3, mp4)\n");
                }
            }
        }
    }

    private void rmf() {
        if (params.length < 1 || params.length > 2) {
            terminalInstance.getClientArea().append("invalid number of parameters, " +
                    "command: rmf should take 1 or 2 parameters" +
                    " (name of file with extension [op. \\a to point to absolute path])\n");
        } else if (!params[0].contains(".")) {
            terminalInstance.getClientArea().append("invalid type of parameter, " +
                    "command: rmf should take 1 or 2 parameters " +
                    "(name of file with extension [op. \\a to point to absolute path])\n");
        } else if (params.length == 2 && !params[1].equals("\\a"))  {
            terminalInstance.getClientArea().append("invalid type of parameter, " +
                    "command: rmf should take 1 or 2 parameters " +
                    "(name of file with extension [op. \\a to point to absolute path])\n");
        } else {
            String[] parts = params[0].split("\\.");
            String name = "";
            for (int i = 0; i < parts.length - 1; i++) {
                name += parts[i];
            }
            String extension = parts[parts.length - 1];

            if (FileSystem.getInstance().getAvailableExtensions().contains(extension)) {
                try {
                    if (params.length == 1) {
                        name = FileSystem.getInstance().getCurrentDirectoryPointer().getFullName() +
                                "\\" + name + "." + extension;
                    } else {
                        name = params[0];
                    }
                    FileSystem.getInstance().removeFile(name);
                    terminalInstance.getClientArea().append("\n");
                } catch (NoSuchFileException e) {
                    terminalInstance.getClientArea().append("can't find such file: " + params[0] + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                terminalInstance.getClientArea().append("invalid file extension " +
                        "(available extensions: txt, png, mp3, mp4)\n");
            }
        }
    }

    private void open() {
        if (params.length < 1 || params.length > 2) {
            terminalInstance.getClientArea().append("invalid number of parameters, " +
                    "command: rmf should take 1 or 2 parameters" +
                    " (name of file with extension [op. \\a to point to absolute path])\n");
        } else if (!params[0].contains(".")) {
            terminalInstance.getClientArea().append("invalid type of parameter, " +
                    "command: rmf should take 1 or 2 parameters " +
                    "(name of file with extension [op. \\a to point to absolute path])\n");
        } else if (params.length == 2 && !params[1].equals("\\a"))  {
            terminalInstance.getClientArea().append("invalid type of parameter, " +
                    "command: rmf should take 1 or 2 parameters " +
                    "(name of file with extension [op. \\a to point to absolute path])\n");
        } else {
            String[] parts = params[0].split("\\.");
            String name = "";
            for (int i = 0; i < parts.length - 1; i++) {
                name += parts[i];
            }
            String extension = parts[parts.length - 1];

            if (FileSystem.getInstance().getAvailableExtensions().contains(extension)) {
                try {
                    if (params.length == 1) {
                        name = FileSystem.getInstance().getCurrentDirectoryPointer().getFullName() +
                                "\\" + name + "." + extension;
                    } else {
                        name = params[0];
                    }
                    openedFileName = name;
                    state = terminalInstance.getClientArea().getText().split("\n");
                    terminalInstance.changeFile(FileSystem.getInstance().readFile(name));

                } catch (NoSuchFileException e) {
                    terminalInstance.getClientArea().append("can't find such file: " + params[0] + "\n");
                    openedFileName = null;
                    state = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    openedFileName = null;
                    state = null;
                }
            } else {
                terminalInstance.getClientArea().append("invalid file extension " +
                        "(available extensions: txt, png, mp3, mp4)\n");
                openedFileName = null;
                state = null;
            }
        }
    }

    private void close() {
        try {
            String []buffer = terminalInstance.getClientArea().getText().split("\n");
            FileSystem.getInstance().writeFile(openedFileName, buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        terminalInstance.getClientArea().setText("");
        for (String line:state) {
            terminalInstance.getClientArea().append(line + "\n");
        }
        openedFileName = null;
        state = null;
    }


    private void mkdir() {
        if (params.length != 1) {
            terminalInstance.getClientArea().append("invalid number of parameters, " +
                    "command: mkdir should take 1 parameter (name of directory without extension)\n");
        } else {
            if (params[0].contains(".")) {
                terminalInstance.getClientArea().append("invalid type of parameter, " +
                        "command: mkdir should take 1 parameter (name of directory without extension)\n");
            } else {
                try {
                    FileSystem.getInstance().createNewFolder(params[0]);
                    terminalInstance.getClientArea().append("\n");
                } catch (IllegalArgumentException e) {
                    terminalInstance.getClientArea().append("directory with the same name has been already created here\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void rmdir() {
        if (params.length < 1 || params.length > 2) {
            terminalInstance.getClientArea().append("invalid number of parameters, " +
                    "command: rmdir should take 1 or 2 parameters" +
                    " (name of directory without extension [op. \\a to point to absolute path])\n");
        } else if (params.length == 1) {
            if (params[0].contains(".")) {
                terminalInstance.getClientArea().append("invalid type of parameter, " +
                        "command: rmdir should take 1 or 2 parameters " +
                        "(name of directory without extension [op. \\a to point to absolute path])\n");
            } else {
                try {
                    String folderName = FileSystem.getInstance()
                            .getCurrentDirectoryPointer().getFullName() + '\\' + params[0];
                    FileSystem.getInstance().deleteFolder(folderName);
                    terminalInstance.getClientArea().append("\n");
                } catch (NoSuchFileException e) {
                    terminalInstance.getClientArea().append("can't find such directory: " + params[0] + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (params[0].contains(".") || !params[1].equals("\\a")) {
                terminalInstance.getClientArea().append("invalid type of parameter, " +
                        "command: rmdir should take 1 or 2 parameters " +
                        "(name of directory without extension [op. \\a to point to absolute path])\n");
            } else {
                try {
                    String folderName = params[0];
                    FileSystem.getInstance().deleteFolder(folderName);
                    terminalInstance.getClientArea().append("\n");
                } catch (NoSuchFileException e) {
                    terminalInstance.getClientArea().append("can't find such directory: " + params[0] + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cd() {
        if (params.length < 1 || params.length > 2) {
            terminalInstance.getClientArea().append("invalid number of parameters, " +
                    "command: cd can take 1 or 2 parameters " +
                    "(\\r to go to root or name of directory (without extension)" +
                    " [op. \\a to point to absolute path])\n");
        } else if (params.length == 1) {
            if (params[0].equals("\\r")) {
                FileSystem.getInstance().resetRootPointer();
                terminalInstance.getClientArea().append("\n");
            } else {
                if (params[0].contains(".")) {
                    terminalInstance.getClientArea().append("invalid type of parameter, " +
                            "command: mkdir should take 1 or 2 parameters " +
                            "(name of directory without extension [op. \\a to point to absolute path])\n");
                } else {
                    String currentPath = FileSystem.getInstance().getCurrentDirectoryPointer().getFullName();
                    String newPath = currentPath + '\\' + params[0];
                    FileSystem.getInstance().moveDirectoryPointer(newPath);
                    if (FileSystem.getInstance().getCurrentDirectoryPointer().getFullName().equals(currentPath)) {
                        terminalInstance.getClientArea().append("can't find such directory: " + params[0] + "\n");
                    } else {
                        terminalInstance.getClientArea().append("\n");
                    }
                }
            }
        } else {
            if (!params[1].equals("\\a")) {
                terminalInstance.getClientArea().append("invalid number of parameters, " +
                        "command: cd can take 1 or 2 parameters " +
                        "(\\r to go to root or name of directory (without extension)" +
                        " [op. \\a to point to absolute path])\n");
            } else {
                if (params[0].contains(".")) {
                    terminalInstance.getClientArea().append("invalid type of parameter, " +
                            "command: rmdir should take 1 or 2 parameters " +
                            "(name of directory without extension [op. \\a to point to absolute path])\n");
                } else {
                    String currentPath = FileSystem.getInstance().getCurrentDirectoryPointer().getFullName();
                    String newPath = params[0];
                    FileSystem.getInstance().moveDirectoryPointer(newPath);
                    if (FileSystem.getInstance().getCurrentDirectoryPointer().getFullName().equals(currentPath)) {
                        terminalInstance.getClientArea().append("can't find such directory: " + params[0] + "\n");
                    } else {
                        terminalInstance.getClientArea().append("\n");
                    }
                }
            }
        }
    }

    private void look() {
        if (params.length > 0) {
            terminalInstance.getClientArea().append("command: look doesn't take any parameters\n");
        } else {
            if (!FileSystem.getInstance().getCurrentDirectoryPointer().getChildren().isEmpty()) {
                terminalInstance.getClientArea().append("\n");
            }
            for (FileSystemObject fileSystemObject:
                    FileSystem.getInstance().getCurrentDirectoryPointer().getChildren()) {
                terminalInstance.getClientArea().append(fileSystemObject.getName() + " ");
            }
            terminalInstance.getClientArea().append("\n");
        }
    }

    private void tree(FileSystemObject base, int level) {
        if (level == 0) {
            terminalInstance.getClientArea().append("\n");
        }
        for (int i = 0; i < level; i++) {
            terminalInstance.getClientArea().append("|");
        }
        if (base.getChildren() == null) {
            terminalInstance.getClientArea().append(base.getName() + "\n");
        } else {
            terminalInstance.getClientArea().append(base.getName() + "\n");
            for (FileSystemObject fileSystemObject: base.getChildren()) {
                tree(fileSystemObject, level + 1);
            }
        }
    }

    private void help() {
        terminalInstance.getClientArea().append("\n<>exit to exit\n");
        terminalInstance.getClientArea().append("<>clear to clear screen\n");
        terminalInstance.getClientArea().append("<>mkf with file name with extension to" +
                " create file in current directory\n");
        terminalInstance.getClientArea().append("<>rmf with file name with extension " +
                "[op. \\a to point to absolute path] to delete file\n");
        terminalInstance.getClientArea().append("<>open with file name with extension " +
                "[op. \\a to point to absolute path] to open file\n");
        terminalInstance.getClientArea().append("<>close to close file\n");
        terminalInstance.getClientArea().append("<>mkdir with directory name (without extension) " +
                "to create subdirectory in current directory\n");
        terminalInstance.getClientArea().append("<>rmdir with directory name (without extension) " +
                "[op. \\a to point to absolute path] to delete directory\n");
        terminalInstance.getClientArea().append("<>cd with directory name (without extension) " +
                "[op. \\a to point to absolute path] or \\r (root) to change directory\n");
        terminalInstance.getClientArea().append("<>look to look at content in current directory\n");
        terminalInstance.getClientArea().append("<>tree to show all contents from root in tree structure\n");
    }
}