package org.acme.models;

public class Chatroom {
    private int id;
    private int userId;
    private int roomId;
    private int supportId;

    public Chatroom() {
    }

    public Chatroom(int id, int userId, int roomId) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSupportId() {
        return supportId;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }
}
