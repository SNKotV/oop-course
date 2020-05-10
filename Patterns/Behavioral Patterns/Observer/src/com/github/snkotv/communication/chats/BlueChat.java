package com.github.snkotv.communication.chats;

public class BlueChat extends Chat {
    private static BlueChat instance;

    private BlueChat() {
        super();
        name = "Blue Chat";
    }

    public static Chat getInstance() {
        if (instance == null) {
            instance = new BlueChat();
        }
        return instance;
    }
}
