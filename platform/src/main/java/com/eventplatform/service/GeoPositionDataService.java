package com.eventplatform.service;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.SerializerException;
import com.eventplatform.factory.GeoPositionFactory;
import com.eventplatform.domain.model.GeoPosition;
import com.eventplatform.repository.GeoPositionDataRepository;
import com.eventplatform.util.container.PojoContainer;
import com.eventplatform.util.serializer.Serializer;
import com.eventplatform.util.serializer.SerializerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(value = "singleton")
@Service
public class GeoPositionDataService implements DataService<GeoPosition> {
    @Autowired
    private Serializer serializer;
    @Autowired
    private GeoPositionFactory geoPositionFactory;
    private GeoPositionDataRepository geoPositionDataRepository;
    private PojoContainer<GeoPosition> container;

    public GeoPositionDataService(GeoPositionDataRepository geoPositionDataRepository) {
        this.container = new PojoContainer<>();
        this.geoPositionDataRepository = geoPositionDataRepository;
        geoPositionDataRepository.findAll().forEach(value -> {
            try {
                container.addValue(value.getId(), value);
            } catch (AlreadyExistsContainerException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public GeoPosition get(int id) throws NotFoundControllerException {
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
    public void create(String text, String textType) throws ControllerException {
        try {
            GeoPosition geoPosition = (GeoPosition) serializer.deserialize(text, SerializerConstants.GEOPOSITION_CLAZZ, textType);
            container.addValue(geoPosition.getId(), geoPosition);
        } catch (SerializerException | AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    public void create(float latitude, float longitude) throws ControllerException {
        try {
            GeoPosition geoPosition = geoPositionFactory.createGeoPosition(latitude, longitude);
            container.addValue(geoPosition.getId(), geoPosition);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public void create(GeoPosition clazz) throws ControllerException {
        try {
            container.addValue(clazz.getId(), clazz);
        } catch (AlreadyExistsContainerException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @Override
    public List<GeoPosition> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }
}
