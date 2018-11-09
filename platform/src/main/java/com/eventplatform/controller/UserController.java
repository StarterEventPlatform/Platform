package com.eventplatform.controller;

import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.model.User;
import com.eventplatform.util.container.UserContainer;

import java.util.List;

public class UserController implements Controller<User> {

    private UserContainer container;

    public UserController() {
        this.container = new UserContainer();
    }

    @Override
    public void create(String JSON) throws ControllerException {

    }

    @Override
    public void create(User clazz) throws ControllerException {

    }

    @Override
    public User get(int id) throws NotFoundControllerException {
        try {
            return container.getValue(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundControllerException();
        }
    }

    @Override
    public void remove(int id) throws NotFoundControllerException {
        try {
            container.remove(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundControllerException();
        }
    }

    @Override
    public List<User> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }
}
