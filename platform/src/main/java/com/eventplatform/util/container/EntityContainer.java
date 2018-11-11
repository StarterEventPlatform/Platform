package com.eventplatform.util.container;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;

import java.util.*;

public class EntityContainer<T> {
    private Map<Integer, T> entities;

    public EntityContainer() {
        entities = new HashMap<>();
    }

    public void addValue(Integer key, T value) throws AlreadyExistsContainerException {
        if (entities.get(key) == null) {
            entities.put(key, value);
        } else {
            throw new AlreadyExistsContainerException();
        }
    }

    public T getValue(Integer key) throws NotFoundContainerException {
        T entity = entities.get(key);
        if (entity != null) {
            return entity;
        } else {
            throw new NotFoundContainerException();
        }
    }

    public void remove(Integer key) throws NotFoundContainerException {
        T entity = entities.get(key);
        if (entity != null) {
            entities.remove(key);
        } else {
            throw new NotFoundContainerException();
        }
    }

    public List<T> getAllValues() throws EmptyContainerException {
        List<T> list = new ArrayList<>(entities.values());
        if (list.size() == 0) {
            throw new EmptyContainerException();
        }
        return Collections.unmodifiableList(list);
    }
}
