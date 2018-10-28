package com.eventplatform.factory;

import com.eventplatform.model.User;

import java.math.BigInteger;
import java.util.Date;

public class UserFactory {

    public static User createUser(User user) {
        return new User(user.getId(), user.getCreationDate(), user.getName(), user.getSurname(), user.getLogin(), user.getEmail(), user.getPassword());
    }

    public static User createUser(BigInteger id, String name, String surname, String login, String email, String password) {
        return new User(id, new Date(System.currentTimeMillis()), name, surname, login, email, password);
    }
}
