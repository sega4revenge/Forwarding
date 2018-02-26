package com.nightshade.forwarding.model;

/**
 * Created by sega4 on 25/02/2018.
 */

public class Sms {
    String sender;
    String text;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
