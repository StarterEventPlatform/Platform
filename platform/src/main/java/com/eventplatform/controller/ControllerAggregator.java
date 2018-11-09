package com.eventplatform.controller;

import com.eventplatform.exception.controller.ControllerAggregatorException;

import java.util.HashMap;
import java.util.Map;

public class ControllerAggregator {
    private Map<String, Controller> controllers;
    private static ControllerAggregator instance;

    private ControllerAggregator() {
        controllers = new HashMap<>();
        controllers.put(ControllerConstants.EVENT_TYPE, new EventController());
        controllers.put(ControllerConstants.GEOPOSITION_TYPE, new GeoPositionController());
        controllers.put(ControllerConstants.MAINTAINER_TYPE, new MaintainerController());
        controllers.put(ControllerConstants.USER_TYPE, new UserController());
    }

    public static ControllerAggregator getInstance() {
        if (instance == null) instance = new ControllerAggregator();
        return instance;
    }

    public Controller getByType(String type) throws ControllerAggregatorException {
        if (type == null || type.equals("")) {
            throw new ControllerAggregatorException();
        }
        return controllers.get(type);
    }

    public void addControllerByType(String type, Controller controller) throws ControllerAggregatorException {
        if (controller == null) {
            throw new ControllerAggregatorException();
        }
        if (type == null || type.equals("")) {
            throw new ControllerAggregatorException();
        }
        if (controllers.get(type) != null) {
            throw new ControllerAggregatorException();
        }
        controllers.put(type, controller);
    }
}
