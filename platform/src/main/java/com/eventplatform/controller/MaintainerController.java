package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.model.Maintainer;
import com.eventplatform.util.Serializer;
import com.eventplatform.util.UtilConstants;
import com.eventplatform.util.container.MaintainerContainer;

import java.util.List;

public class MaintainerController implements Controller<Maintainer> {
    private Serializer serializer;
    private MaintainerContainer container;

    public MaintainerController() {
        this.container = new MaintainerContainer();
        this.serializer = Serializer.getInstance();
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

    @Override
    public void create(String text, String textType) throws ControllerException {
        try {
            Maintainer maintainer = (Maintainer) serializer.deserialize(text, UtilConstants.MAINTAINER_CLAZZ, textType);
            container.addValue(maintainer.getId(), maintainer);
        } catch (SerializerException | AlreadyExistsContainerException e) {
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
