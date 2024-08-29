package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.Room;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoomService {
    @Inject
    DataSource dataSource;

    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getInt("id"));
                room.setRoomName(resultSet.getString("room_name"));
                rooms.add(room);
            }
        }
        return rooms;
    }

    public Integer getRoomId(String roomName) throws SQLException {
        String query = "SELECT id FROM Room WHERE room_name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, roomName);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return id;
                }
                else {
                    return null;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public String getRoomNameById(int id) throws SQLException {
        String query = "SELECT room_name FROM Room WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String roomName = rs.getString("room_name");
                    return roomName;
                }
                else {
                    return null;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
