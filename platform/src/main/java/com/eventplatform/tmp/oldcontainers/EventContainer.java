package com.eventplatform.tmp.oldcontainers;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.pojo.klass.Event;

import java.util.*;

public class EventContainer implements Container<Event> {
    private Map<Integer, Event> events;

    public EventContainer() {
        events = new HashMap<>();
    }

    @Override
    public void addValue(Integer key, Event value) throws AlreadyExistsContainerException {
        if (events.get(key) == null) {
            events.put(key, value);
        } else {
            throw new AlreadyExistsContainerException();
        }
    }

    @Override
    public Event getValue(Integer key) throws NotFoundContainerException {
        Event event = events.get(key);
        if (event != null) {
            return event;
        } else {
            throw new NotFoundContainerException();
        }
    }

    @Override
    public void remove(Integer key) throws NotFoundContainerException {
        Event event = events.get(key);
        if (event != null) {
            events.remove(key);
        } else {
            throw new NotFoundContainerException();
        }
    }

    @Override
    public List<Event> getAllValues() throws EmptyContainerException {
        List<Event> list = new ArrayList<>(events.values());
        if (list.size() == 0) {
            throw new EmptyContainerException();
        }
        return Collections.unmodifiableList(list);
    }
}