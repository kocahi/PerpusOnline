package com.example.perpusonline.Model;

public class Message {
    private String message;
    private boolean isFromMe;
    //ALT INS GETTER
    public String getMessage() {
        return message;
    }

    public boolean isFromMe() {
        return isFromMe;
    }
    //ALT INS CONSTRUCTOR
    public Message(String message, boolean isFromMe) {
        this.message = message;
        this.isFromMe = isFromMe;
    }
}
