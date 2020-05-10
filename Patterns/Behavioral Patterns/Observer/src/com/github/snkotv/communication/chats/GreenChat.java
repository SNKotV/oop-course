package com.github.snkotv.communication.chats;

public class GreenChat extends Chat {
    private static GreenChat instance;

    private GreenChat() {
        super();
        name = "Green Chat";
    }

    public static Chat getInstance() {
        if (instance == null) {
            instance = new GreenChat();
        }
        return instance;
    }
}
