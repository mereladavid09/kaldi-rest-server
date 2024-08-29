package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.models.User;

import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class UserService {
    @Inject
    DataSource dataSource;

    public User getUserById(int userId){
        String query = "Select * FROM Users where id = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                User user = new User();
                user.setId(userId);
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }


    public User getUserByUsername(String username){
       String query = "Select * FROM Users where username = ?";
       try {
           Connection connection = dataSource.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,username);
           ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               User user = new User();
               user.setId(resultSet.getInt("id"));
               user.setUsername(username);
               user.setEmail(resultSet.getString("email"));
               user.setRole(resultSet.getString("role"));
               return user;
           }
       }catch (SQLException sqlException){
           sqlException.printStackTrace();
       }
       return null;
    }
    public String getUserRole(String username) {
        String sql = "SELECT role FROM Users WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
