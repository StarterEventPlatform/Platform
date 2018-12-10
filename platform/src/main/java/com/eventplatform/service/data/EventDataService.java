package com.eventplatform.service.data;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.exception.dataservice.NotFoundDataServiceException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.domain.model.Event;
import com.eventplatform.util.container.PojoContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(value = "singleton")
@Service
public class EventDataService implements DataService<Event> {
    @Autowired
    private Serializer serializer;
    private PojoContainer container;

    public EventDataService() {
        this.container = new PojoContainer<Event>();
    }

    @Override
    public Event get(int id) throws NotFoundDataServiceException {
        try {
            return (Event) container.getValue(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundDataServiceException();
        }
    }

    @Override
    public void remove(int id) throws NotFoundDataServiceException {
        try {
            container.remove(id);
        } catch (NotFoundContainerException e) {
            throw new NotFoundDataServiceException();
        }
    }

    @Override
    public List<Event> getAll() throws EmptyDataServiceException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyDataServiceException();
        }
    }

    public void create(String text, String textType) throws DataServiceException {
        try {
            Event event = (Event) serializer.deserialize(text, SerializerConstants.EVENT_CLAZZ, textType);
            container.addValue(event.getId(), event);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }

    @Override
    public void create(Event clazz) throws DataServiceException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new DataServiceException(e.getMessage());
        }
    }
}
