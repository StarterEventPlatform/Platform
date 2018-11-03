package com.eventplatform.factory;

import com.eventplatform.model.Event;
import com.eventplatform.model.GeoPosition;
import com.eventplatform.model.Maintainer;
import com.eventplatform.model.User;

import java.util.Date;
import java.util.List;

public class MaintainerFactory {

    public static Maintainer createMaintainer(Maintainer maintainer) {
        return new Maintainer(maintainer.getId(), maintainer.getCreationDate(), maintainer.getUser(), maintainer.getName(), maintainer.getDescription(), maintainer.getEvents(), maintainer.getGeoPosition());
    }

    public static Maintainer createMaintainer(int id, User user, String name, String description, List<Event> events, GeoPosition geoPosition) {
        return new Maintainer(id, new Date(System.currentTimeMillis()), user, name, description, events, geoPosition);
    }

    public static Maintainer createMaintainer(int id, User user, String name, String description, GeoPosition geoPosition) {
        return new Maintainer(id, new Date(System.currentTimeMillis()), user, name, description, geoPosition);
    }
}
