package com.eventplatform.model;

import java.util.*;

public class Maintainer extends Entity {

    private User user;
    private String name;
    private String description;
    private List<Event> events;
    private GeoPosition geoPosition;

    public Maintainer(int id, Date creationDate) {
        super(id, creationDate);
        this.events = new ArrayList<>();
    }

    public Maintainer(int id, Date creationDate, User user, String name, String description, List<Event> events, GeoPosition geoPosition) {
        super(id, creationDate);
        this.user = user;
        this.name = name;
        this.description = description;
        this.events = events;
        this.geoPosition = geoPosition;
    }

    public Maintainer(int id, Date creationDate, User user, String name, String description, GeoPosition geoPosition) {
        super(id, creationDate);
        this.user = user;
        this.name = name;
        this.description = description;
        this.geoPosition = geoPosition;
        this.events = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void removeEvent(int id) {
        for (Event e : events) {
            if (e.getId() == id) {
                events.remove(e);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maintainer)) return false;
        Maintainer that = (Maintainer) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCreationDate(), getUser(), getName(), getDescription(), getEvents(), getGeoPosition());
    }

    @Override
    public String toString() {
        return "Maintainer{" +
                "id='" + super.getId() + '\'' +
                "user=" + user + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", events=" + events + '\'' +
                ", geoPosition=" + geoPosition + '\'' +
                ", creationDate='" + super.getCreationDate() +
                '}';
    }
}
