package com.eventplatform.pojo.controller;

import com.eventplatform.exception.controller.ControllerAggregatorException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Scope(value = "singleton")
@Component
public class DataControllerAggregator {
    private Map<String, DataController> controllers;

    public DataControllerAggregator() {
        controllers = new HashMap<>();
        controllers.put(DataControllerConstants.EVENT_TYPE, new EventDataController());
        controllers.put(DataControllerConstants.GEOPOSITION_TYPE, new GeoPositionDataController());
        controllers.put(DataControllerConstants.MAINTAINER_TYPE, new MaintainerDataController());
        controllers.put(DataControllerConstants.USER_TYPE, new UserDataController());
    }

    public DataController getByType(String type) throws ControllerAggregatorException {
        if (type == null || type.equals("")) {
            throw new ControllerAggregatorException();
        }
        return controllers.get(type);
    }

    public void addControllerByType(String type, DataController dataController) throws ControllerAggregatorException {
        if (dataController == null) {
            throw new ControllerAggregatorException();
        }
        if (type == null || type.equals("")) {
            throw new ControllerAggregatorException();
        }
        if (controllers.get(type) != null) {
            throw new ControllerAggregatorException();
        }
        controllers.put(type, dataController);
    }
}
