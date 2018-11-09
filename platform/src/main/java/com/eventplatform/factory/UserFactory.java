package com.eventplatform.factory;

import com.eventplatform.model.User;

import java.util.Date;
import java.util.Map;

public class UserFactory {

    public static User createUser(User user) {
        return new User(user.getId(), user.getCreationDate(), user.getName(), user.getSurname(), user.getLogin(), user.getEmail(), user.getPassword());
    }

    public static User createUser(int id, String name, String surname, String login, String email, String password) {
        return new User(id, new Date(System.currentTimeMillis()), name, surname, login, email, password);
    }

    public static User createUser(Map<String, String> params) {
        return new User(Integer.parseInt(params.get("id")), new Date(Long.parseLong(params.get("creationDate"))), params.get("name"), params.get("surname"), params.get("login"),
                params.get("email"), params.get("password"));
    }
}
