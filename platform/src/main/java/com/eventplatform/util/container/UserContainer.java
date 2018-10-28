package com.eventplatform.util.container;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.model.User;

import java.util.*;


// todo move to bean
public class UserContainer implements Container<User> {

    private Map<Integer, User> users;
    private static UserContainer instance;

    private UserContainer() {
        users = new HashMap<>();
    }

    public static UserContainer getInstance() {
        if (instance == null) instance = new UserContainer();
        return instance;
    }

    @Override
    public void addValue(Integer key, User value) throws AlreadyExistsContainerException {
        if (users.get(key) == null) {
            users.put(key, value);
        } else {
            throw new AlreadyExistsContainerException();
        }
    }

    @Override
    public User getValue(Integer key) throws NotFoundContainerException {
        User user = users.get(key);
        if (user != null) {
            return user;
        } else {
            throw new NotFoundContainerException();
        }
    }

    @Override
    public void remove(Integer key) throws NotFoundContainerException {
        User user = users.get(key);
        if (user != null) {
            users.remove(key);
        } else {
            throw new NotFoundContainerException();
        }
    }

    @Override
    public List<User> getAllValues() throws EmptyContainerException {
        List<User> list = new ArrayList<>(users.values());
        if (list.size() == 0) {
            throw new EmptyContainerException();
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public String toString() {
        return "UserContainer{" +
                "users=" + users +
                '}';
    }
}
