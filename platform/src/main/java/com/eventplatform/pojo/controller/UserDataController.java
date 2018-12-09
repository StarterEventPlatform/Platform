package com.eventplatform.pojo.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.PasswordEncoderException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.factory.UserFactory;
import com.eventplatform.pojo.klass.User;
import com.eventplatform.repository.UserDataRepository;
import com.eventplatform.util.container.PojoContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "singleton")
@Component
public class UserDataController implements DataController<User> {
    @Autowired
    private Serializer serializer;
    @Autowired
    private UserFactory userFactory;
    private UserDataRepository userDataRepository;
    private PojoContainer<User> container;

    public UserDataController(UserDataRepository userDataRepository) {
        this.container = new PojoContainer<>();
        this.userDataRepository = userDataRepository;
        // todo remove then
        userDataRepository.findAll().forEach(v -> {
            try {
                container.addValue(v.getId(), v);
            } catch (AlreadyExistsContainerException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void create(String text, String textType) throws ControllerException {
        try {
            User user = (User) serializer.deserialize(text, SerializerConstants.USER_CLAZZ, textType);
            container.addValue(user.getId(), user);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public void create(String name, String surname, String login, String email, String password) throws
            ControllerException {
        try {
            User user = userFactory.createUser(name, surname, login, email, password, "default");
            container.addValue(user.getId(), user);
        } catch (AlreadyExistsContainerException | PasswordEncoderException e) {
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
