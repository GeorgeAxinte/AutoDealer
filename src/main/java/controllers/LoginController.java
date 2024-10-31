package controllers;

import database.DatabaseConnection;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.EncryptionUtil;

public class LoginController {
    private List<User> users;

    public LoginController() {
        users = new ArrayList<>();
    }





    public boolean register(String username, String password) {
        if (username != null && password != null && !userExists(username)) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2,
                        EncryptionUtil.hashPassword(password));
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean userExists(String username) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int login(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, password FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                int userId = resultSet.getInt("id");

                if (EncryptionUtil.checkPassword(password, hashedPassword)) {
                    return userId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}