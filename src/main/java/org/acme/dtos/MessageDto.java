package org.acme.dtos;

import org.acme.models.SenderType;

import java.sql.Timestamp;

public class MessageDto {
    private int chatroomId;
    private SenderType senderType;
    private Timestamp sentTimestamp;
    private String message;

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
