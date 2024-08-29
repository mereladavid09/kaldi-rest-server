package org.acme.respons;

import org.acme.models.Message;
import org.acme.models.User;

import java.util.List;

public class JoinChatroomResponse {
    User user;

    List<Message> message;

    String roomName;

    public JoinChatroomResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
