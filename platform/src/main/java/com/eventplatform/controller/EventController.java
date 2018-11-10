package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.JsonParserException;
import com.eventplatform.factory.EventFactory;
import com.eventplatform.model.Event;
import com.eventplatform.util.JsonParser;
import com.eventplatform.util.container.EventContainer;

import java.util.List;
import java.util.Map;

public class EventController implements Controller<Event> {
    private JsonParser jsonParser;
    private EventContainer container;

    public EventController() {
        this.container = new EventContainer();
        this.jsonParser = JsonParser.getJsonParser();
    }

    @Override
    public Event get(int id) throws NotFoundControllerException {
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
    public List<Event> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }

    @Override
    public void create(String JSON) throws ControllerException {
      /*  try {
            Map<String, String> eventParams = jsonParser.getParsedJson(JSON);
            Event event = EventFactory.createEvent(eventParams);
            container.addValue(event.getId(), event);
        } catch (JsonParserException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }*/
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
