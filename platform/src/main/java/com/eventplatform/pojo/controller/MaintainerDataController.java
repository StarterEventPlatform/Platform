package com.eventplatform.pojo.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.factory.MaintainerFactory;
import com.eventplatform.pojo.klass.GeoPosition;
import com.eventplatform.pojo.klass.Maintainer;
import com.eventplatform.pojo.klass.User;
import com.eventplatform.repository.MaintainerDataRepository;
import com.eventplatform.util.container.PojoContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "singleton")
@Component
public class MaintainerDataController implements DataController<Maintainer> {
    @Autowired
    private Serializer serializer;
    @Autowired
    private MaintainerFactory maintainerFactory;
    private MaintainerDataRepository maintainerDataRepository;
    private PojoContainer<Maintainer> container;

    public MaintainerDataController(MaintainerDataRepository maintainerDataRepository) {
        this.container = new PojoContainer<>();
        this.maintainerDataRepository = maintainerDataRepository;
        maintainerDataRepository.findAll().forEach(value -> {
            try {
                container.addValue(value.getId(), value);
            } catch (AlreadyExistsContainerException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public Maintainer get(int id) throws NotFoundControllerException {
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
    public List<Maintainer> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }

    public void create(String text, String textType) throws ControllerException {
        try {
            Maintainer maintainer = (Maintainer) serializer.deserialize(text, SerializerConstants.MAINTAINER_CLAZZ, textType);
            container.addValue(maintainer.getId(), maintainer);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public void create(String name, String description, User user, GeoPosition geoPosition) throws ControllerException {
        try {
            Maintainer maintainer = maintainerFactory.createMaintainer(name,description, user, geoPosition);
            container.addValue(maintainer.getId(),maintainer);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void create(Maintainer clazz) throws ControllerException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
