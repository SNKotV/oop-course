package com.github.snkotv.communication.chats;

public class RedChat extends Chat {
    private static RedChat instance;

    private RedChat() {
        super();
        name = "Red Chat";
    }

    public static Chat getInstance() {
        if (instance == null) {
            instance = new RedChat();
        }
        return instance;
    }
}
