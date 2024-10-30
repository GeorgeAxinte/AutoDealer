package controllers;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(utils.EncryptionUtil.hashPassword(password))) {
            return true;
        }
        return false;
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }

        User newUser = new User(username, utils.EncryptionUtil.hashPassword(password));
        newUser.setUserType("Client");
        addUser(newUser);
        return true;
    }

    public String getUserType(String username) {
        User user = users.get(username);
        return user != null ? user.getUserType() : null;
    }

    public void listUsers() {
        for (User user : users.values()) {
            System.out.println(user.getUsername());
        }
    }
}
