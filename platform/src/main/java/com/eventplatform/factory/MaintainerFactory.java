package com.eventplatform.factory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class MaintainerFactory {
/*
    public static Maintainer createMaintainer(Maintainer maintainer) {
        return new Maintainer(maintainer.getId(), maintainer.getCreationDate(), maintainer.getUser(), maintainer.getName(), maintainer.getDescription(), maintainer.getEvents(), maintainer.getGeoPosition());
    }

    public static Maintainer createMaintainer(int id, User user, String name, String description, List<Event> events, GeoPosition geoPosition) {
        return new Maintainer(id, new Date(System.currentTimeMillis()), user, name, description, events, geoPosition);
    }

    public static Maintainer createMaintainer(int id, User user, String name, String description, GeoPosition geoPosition) {
        return new Maintainer(id, new Date(System.currentTimeMillis()), user, name, description, geoPosition);
    }*/
}
