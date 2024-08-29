package org.acme.dtos;

public class ChatroomDto {
    private int id;
    private int userId;
    private int roomId;

    private int supportId;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
