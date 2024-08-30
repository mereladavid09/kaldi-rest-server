package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class AuthService {

    @Inject
    DataSource dataSource;


    public boolean validateSupport(String username, String password){
        String getSupportPassQuery = "SELECT support_password FROM Support WHERE username = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatementSupport = connection.prepareStatement(getSupportPassQuery);
            preparedStatementSupport.setString(1,username);

            ResultSet resultSetSupport = preparedStatementSupport.executeQuery();

            if (resultSetSupport.next()){
                return BCrypt.checkpw(password, resultSetSupport.getString("support_password"));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }

    public boolean validateUser(String username, String password){
        String getUserPassQuery = "SELECT user_password FROM Users WHERE username = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatementUser = connection.prepareStatement(getUserPassQuery);
            preparedStatementUser.setString(1,username);
            ResultSet resultSetUser = preparedStatementUser.executeQuery();
            if(resultSetUser.next()){
                return BCrypt.checkpw(password, resultSetUser.getString("user_password"));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
