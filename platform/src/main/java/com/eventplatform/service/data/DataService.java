package com.eventplatform.service.data;

import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.exception.dataservice.NotFoundDataServiceException;

import java.util.List;

public interface DataService<T> {
    public void create(T clazz) throws DataServiceException;

    public void create(String text, String textType) throws DataServiceException;

    public T get(int id) throws NotFoundDataServiceException;

    public void remove(int id) throws NotFoundDataServiceException;

    public List<T> getAll() throws EmptyDataServiceException;
}
