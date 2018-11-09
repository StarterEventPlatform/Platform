package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.JsonParserException;
import com.eventplatform.factory.UserFactory;
import com.eventplatform.model.User;
import com.eventplatform.util.JsonParser;
import com.eventplatform.util.container.UserContainer;

import java.util.List;
import java.util.Map;

public class UserController implements Controller<User> {
    private JsonParser jsonParser;
    private UserContainer container;

    public UserController() {
        this.container = new UserContainer();
        this.jsonParser = JsonParser.getJsonParser();
    }

    @Override
    public void create(String JSON) throws ControllerException {
        try {
            Map<String, String> userParams = jsonParser.getParsedJson(JSON);
            User user = UserFactory.createUser(userParams);
            container.addValue(user.getId(), user);
        } catch (JsonParserException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void create(User clazz) throws ControllerException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
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
