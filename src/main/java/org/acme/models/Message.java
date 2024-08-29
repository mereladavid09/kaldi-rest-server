package org.acme.models;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int chatroomId;
    private SenderType senderType;
    private Timestamp sentTimestamp;
    private String message;

    public Message() {
    }

    public Message(int chatroomId, SenderType senderType, Timestamp sentTimestamp, String message) {
        this.chatroomId = chatroomId;
        this.senderType = senderType;
        this.sentTimestamp = sentTimestamp;
        this.message = message;
    }

    public Message(int id, int chatroomId, Timestamp sentTimestamp, String message , SenderType userType) {
        this.id = id;
        this.chatroomId = chatroomId;
        this.sentTimestamp = sentTimestamp;
        this.message = message;
        this.senderType = userType;
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

    public SenderType getSenderType() {
        return senderType;
    }

    public void setSenderType(SenderType senderType) {
        this.senderType = senderType;
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
