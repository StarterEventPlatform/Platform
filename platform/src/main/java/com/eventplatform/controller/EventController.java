package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.model.Event;
import com.eventplatform.util.container.EntityContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;

import java.util.List;

public class EventController implements Controller<Event> {
    private Serializer serializer;
    private EntityContainer container;

    public EventController() {
        this.container = new EntityContainer<Event>();
        this.serializer = Serializer.getInstance();
    }

    @Override
    public Event get(int id) throws NotFoundControllerException {
        try {
            return (Event) container.getValue(id);
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
    public List<Event> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }

    @Override
    public void create(String text, String textType) throws ControllerException {
        try {
            Event event = (Event) serializer.deserialize(text, SerializerConstants.EVENT_CLAZZ, textType);
            container.addValue(event.getId(), event);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void create(Event clazz) throws ControllerException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }
}
