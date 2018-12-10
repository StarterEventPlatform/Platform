package com.eventplatform.service.data;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.exception.dataservice.NotFoundDataServiceException;
import com.eventplatform.exception.utils.PasswordEncoderException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.factory.UserFactory;
import com.eventplatform.domain.model.User;
import com.eventplatform.repository.UserDataRepository;
import com.eventplatform.util.container.PojoContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(value = "singleton")
@Service
public class UserDataService implements DataService<User> {
    @Autowired
    private Serializer serializer;
    @Autowired
    private UserFactory userFactory;
    private UserDataRepository userDataRepository;
    private PojoContainer<User> container;

    public UserDataService(UserDataRepository userDataRepository) {
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
    public void create(String text, String textType) throws DataServiceException {
        try {
            User user = (User) serializer.deserialize(text, SerializerConstants.USER_CLAZZ, textType);
            container.addValue(user.getId(), user);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }

    public void create(String name, String surname, String login, String email, String password) throws
            DataServiceException {
        try {
            User user = userFactory.createUser(name, surname, login, email, password, "default");
            container.addValue(user.getId(), user);
        } catch (AlreadyExistsContainerException | PasswordEncoderException e) {
            throw new DataServiceException(e.getMessage());
        }
    }

    @Override
    public void create(User clazz) throws DataServiceException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }

    @Override
    public User get(int id) throws NotFoundDataServiceException {
        try {
            return container.getValue(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundDataServiceException();
        }
    }

    @Override
    public void remove(int id) throws NotFoundDataServiceException {
        try {
            container.remove(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundDataServiceException();
        }
    }

    @Override
    public List<User> getAll() throws EmptyDataServiceException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyDataServiceException();
        }
    }
}
