package com.eventplatform.model;

import java.util.*;

public class Event extends Entity {

    private String name;
    private String description;
    private GeoPosition geoPosition;
    private List<Maintainer> maintainers;
    private String type;
    private Date eventDate;

    public Event() {
    }

    public Event(int id, Date creationDate) {
        super(id, creationDate);
    }

    public Event(int id, Date creationDate, String name, String description, GeoPosition geoPosition, List<Maintainer> maintainers, String type, Date eventDate) {
        super(id, creationDate);
        this.name = name;
        this.description = description;
        this.geoPosition = geoPosition;
        this.maintainers = maintainers;
        this.type = type;
        this.eventDate = eventDate;
    }

    public Event(int id, Date creationDate, String name, String description, GeoPosition geoPosition, String type, Date eventDate) {
        super(id, creationDate);
        this.name = name;
        this.description = description;
        this.geoPosition = geoPosition;
        this.maintainers = new ArrayList<>();
        this.type = type;
        this.eventDate = eventDate;
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

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public List<Maintainer> getMaintainers() {
        return Collections.unmodifiableList(maintainers);
    }

    public void addMaintainer(Maintainer maintainer) {
        maintainers.add(maintainer);
    }

    public void removeMaintainer(int id) {
        for (Maintainer m : maintainers) {
            if (m.getId() == id) {
                maintainers.remove(m);
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(getCreationDate(), event.getCreationDate()) &&
                Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreationDate(), getName(), getDescription(), getGeoPosition(), getMaintainers(), getType(), getEventDate());
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", geoPosition=" + geoPosition +
                ", maintainers=" + maintainers +
                ", type='" + type + '\'' +
                ", eventDate=" + eventDate + '\'' +
                ", creationDate='" + super.getCreationDate() +
                '}';
    }
}
