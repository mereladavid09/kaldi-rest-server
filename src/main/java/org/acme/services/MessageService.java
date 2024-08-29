package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dtos.MessageDto;
import org.acme.models.Message;
import org.acme.models.SenderType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class MessageService {
    @Inject
    DataSource dataSource;

    public void sentMessage(MessageDto messageDto){
        String query= "INSERT INTO Message (chatroom_id,sender_type,message,timestamp) VALUES (?,?,?,?)";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,messageDto.getChatroomId());
            preparedStatement.setString(2,messageDto.getSenderType().toString());
            preparedStatement.setString(3,messageDto.getMessage());
            preparedStatement.setTimestamp(4,messageDto.getSentTimestamp());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public List<Message> getAllMessagesInChatroom(int chatroom_id){
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Message where chatroom_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1,chatroom_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setChatroomId(resultSet.getInt("chatroom_id"));
                message.setSenderType(SenderType.valueOf(resultSet.getString("sender_type")));
                message.setMessage(resultSet.getString("message"));
                message.setSentTimestamp(resultSet.getTimestamp("timestamp"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
