package com.eventplatform.pojo.controller;

import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;

import java.util.List;

public interface DataController<T> {
    public void create(T clazz) throws ControllerException;

    public void create(String text, String textType) throws ControllerException;

    public T get(int id) throws NotFoundControllerException;

    public void remove(int id) throws NotFoundControllerException;

    public List<T> getAll() throws EmptyControllerException;
}
