package com.eventplatform.controller;

import com.eventplatform.exception.container.AlreadyExistsContainerException;
import com.eventplatform.exception.container.EmptyContainerException;
import com.eventplatform.exception.container.NotFoundContainerException;
import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.exception.controller.NotFoundControllerException;
import com.eventplatform.exception.utils.JsonParserException;
import com.eventplatform.factory.GeoPosistionFactory;
import com.eventplatform.model.GeoPosition;
import com.eventplatform.util.JsonParser;
import com.eventplatform.util.container.GeoPositionContainer;

import java.util.List;
import java.util.Map;

public class GeoPositionController implements Controller<GeoPosition> {
    private JsonParser jsonParser;
    private GeoPositionContainer container;

    public GeoPositionController() {
        this.container = new GeoPositionContainer();
        this.jsonParser = JsonParser.getJsonParser();
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
    public List<GeoPosition> getAll() throws EmptyControllerException {
        try {
            return container.getAllValues();
        } catch (EmptyContainerException e) {
            throw new EmptyControllerException();
        }
    }

    @Override
    public void create(String JSON) throws ControllerException {
        try {
            Map<String, String> geoPositionParams = jsonParser.getParsedJson(JSON);
            GeoPosition geoPosition = GeoPosistionFactory.createGeoPosition(geoPositionParams);
            container.addValue(geoPosition.getId(), geoPosition);
        } catch (JsonParserException | AlreadyExistsContainerException e) {
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
}
