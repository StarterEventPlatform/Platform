package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.model.GeoPosition;
import com.eventplatform.util.Serializer;
import com.eventplatform.util.UtilConstants;
import com.eventplatform.util.container.GeoPositionContainer;

import java.util.List;

public class GeoPositionController implements Controller<GeoPosition> {
    private Serializer serializer;
    private GeoPositionContainer container;

    public GeoPositionController() {
        this.container = new GeoPositionContainer();
        this.serializer = Serializer.getInstance();
    }

    @Override
    public GeoPosition get(int id) throws NotFoundControllerException {
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
    public void create(String text, String textType) throws ControllerException {
        try {
            GeoPosition geoPosition = (GeoPosition) serializer.deserialize(text, UtilConstants.GEOPOSITION_CLAZZ, textType);
            container.addValue(geoPosition.getId(), geoPosition);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void create(GeoPosition clazz) throws ControllerException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<GeoPosition> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }
}
