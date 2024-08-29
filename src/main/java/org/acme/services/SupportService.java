package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.acme.models.Support;
@ApplicationScoped
public class SupportService {
    @Inject
    DataSource dataSource;

    public Support getSupportById(int id){
        String query = "Select * FROM Support where id = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Support support = new Support();
                support.setId(id);
                support.setUsername(resultSet.getString("username"));
                support.setEmail(resultSet.getString("email"));
                support.setRole(resultSet.getString("role"));
                return support;
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public Support getSupportByUsername(String username){
        String query = "SELECT * FROM Support WHERE username = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Support support = new Support();
                support.setId(resultSet.getInt("id"));
                support.setEmail(resultSet.getString("email"));
                support.setUsername(resultSet.getString("username"));
                return support;
            }
            else {return null;}
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }
    }

    public String getSupportRole(String username) {
        String sql = "SELECT role FROM Support WHERE username = ?";
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
