package com.eventplatform.controller;

import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.model.GeoPosition;

import java.util.List;

public class GeoPositionController implements Controller<GeoPosition> {
    @Override
    public GeoPosition get(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<GeoPosition> getAll() {
        return null;
    }

    @Override
    public void create(String JSON) throws ControllerException {

    }

    @Override
    public void create(GeoPosition clazz) throws ControllerException {

    }
}
