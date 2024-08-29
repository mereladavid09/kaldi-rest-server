package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dtos.ChatroomDto;
import org.acme.models.Chatroom;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ChatroomService {
    @Inject
    DataSource dataSource;

    public void createChatroom(ChatroomDto chatroomDto){
        String query = "INSERT INTO Chatroom (user_id,room_id) VALUES(?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,chatroomDto.getUserId());
            preparedStatement.setInt(2,chatroomDto.getRoomId());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void joinChatroom(ChatroomDto chatroomDto){
        String query = "UPDATE Chatroom SET support_id = ? WHERE id = ?;";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,chatroomDto.getSupportId());
            preparedStatement.setInt(2,chatroomDto.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public List<Chatroom> getUnansweredChatrooms(){
        String query = "SELECT * FROM Chatroom WHERE support_id = NULL";
        List<Chatroom> chatrooms = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Chatroom chatroom = new Chatroom();
                chatroom.setId(resultSet.getInt("id"));
                chatroom.setUserId(resultSet.getInt("user_id"));
                chatroom.setRoomId(resultSet.getInt("room_id"));
                chatrooms.add(chatroom);
            }
            return chatrooms;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public List<Chatroom> getChatroomByUserId(int userId){
        String query = "SELECT * FROM Chatroom WHERE user_id = ?";
        List<Chatroom> chatrooms = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Chatroom chatroom = new Chatroom();
                chatroom.setId(resultSet.getInt("id"));
                chatroom.setUserId(userId);
                chatroom.setRoomId(resultSet.getInt("room_id"));
                chatroom.setSupportId(resultSet.getInt("support_id"));
                chatrooms.add(chatroom);
            }
            return chatrooms;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
    public List<Chatroom> getChatroomBySupportId(int supportId){
        String query = "SELECT * FROM Chatroom WHERE support_id = ?";
        List<Chatroom> chatrooms = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,supportId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Chatroom chatroom = new Chatroom();
                chatroom.setId(resultSet.getInt("id"));
                chatroom.setUserId(supportId);
                chatroom.setRoomId(resultSet.getInt("room_id"));
                chatroom.setSupportId(resultSet.getInt("support_id"));
                chatrooms.add(chatroom);
            }
            return chatrooms;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
}
