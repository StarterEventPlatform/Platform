package com.eventplatform.factory;

import com.eventplatform.model.Event;
import com.eventplatform.model.GeoPosition;
import com.eventplatform.model.Maintainer;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventFactory {

    public static Event createEvent(Event event) {
        return new Event(event.getId(), event.getCreationDate(), event.getName(), event.getDescription(), event.getGeoPosition(), event.getMaintainers(), event.getType(), event.getEventDate());
    }

    public static Event createEvent(int id, String name, String description, GeoPosition geoPosition, List<Maintainer> maintainers, String type, Date eventDate) {
        return new Event(id, new Date(System.currentTimeMillis()), name, description, geoPosition, maintainers, type, eventDate);
    }

    public static Event createEvent(int id, String name, String description, GeoPosition geoPosition, String type, Date eventDate) {
        return new Event(id, new Date(System.currentTimeMillis()), name, description, geoPosition, type, eventDate);
    }
    /*
    public static Event createEvent(Map<String, String> params) {
        return new Event(Integer.parseInt(params.get("id")),new Date(Long.parseLong("creationDate")),params.get("name"), params.get("description"),
                GeoPosistionFactory.createGeoPosition(params.get("geoPosition")),params.get("type"),new Date(Long.parseLong("eventDate")));
    }*/
}
