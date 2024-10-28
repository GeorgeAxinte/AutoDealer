package controllers;

import models.User;
import utils.EncryptionUtil;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private List<User> users;

    public LoginController() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {

                return EncryptionUtil.checkPassword(password, user.getPassword());
            }
        }
        return false;
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}