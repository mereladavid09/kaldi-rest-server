package org.acme.models;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int chatroomId;
    private Timestamp sentTimestamp;
    private String message;

    public Message() {
    }
    public Message(int id, int chatroomId, Timestamp sentTimestamp, String message) {
        this.id = id;
        this.chatroomId = chatroomId;
        this.sentTimestamp = sentTimestamp;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }

    public Timestamp getSentTimestamp() {
        return sentTimestamp;
    }

    public void setSentTimestamp(Timestamp sentTimestamp) {
        this.sentTimestamp = sentTimestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
