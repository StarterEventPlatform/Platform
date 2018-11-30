package com.eventplatform.util.container;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;

import java.util.*;

public class PojoContainer<T> {
    private Map<Integer, T> data;

    public PojoContainer() {
        data = new HashMap<>();
    }

    public void addValue(Integer key, T value) throws AlreadyExistsContainerException {
        if (data.get(key) == null) {
            data.put(key, value);
        } else {
            throw new AlreadyExistsContainerException();
        }
    }

    public T getValue(Integer key) throws NotFoundContainerException {
        T entity = data.get(key);
        if (entity != null) {
            return entity;
        } else {
            throw new NotFoundContainerException();
        }
    }

    public void remove(Integer key) throws NotFoundContainerException {
        T entity = data.get(key);
        if (entity != null) {
            data.remove(key);
        } else {
            throw new NotFoundContainerException();
        }
    }

    public List<T> getAllValues() throws EmptyContainerException {
        List<T> list = new ArrayList<>(data.values());
        if (list.size() == 0) {
            throw new EmptyContainerException();
        }
        return Collections.unmodifiableList(list);
    }
}
