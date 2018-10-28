package com.eventplatform.util.container;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;

import java.util.List;

public interface Container<T> {
    public void addValue(Integer key, T value) throws AlreadyExistsContainerException;

    public T getValue(Integer key) throws NotFoundContainerException;

    public void remove(Integer key) throws NotFoundContainerException;

    public List<T> getAllValues() throws EmptyContainerException;
}
