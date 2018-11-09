package com.eventplatform.controller;

import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.model.Event;

import java.util.List;

public class EventController implements Controller<Event> {

    @Override
    public Event get(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Event> getAll() {
        return null;
    }

    @Override
    public void create(String JSON) throws ControllerException {

    }

    @Override
    public void create(Event clazz) throws ControllerException {

    }
}
