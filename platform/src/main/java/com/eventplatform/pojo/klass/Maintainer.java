package com.eventplatform.pojo.klass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Maintainer {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private Date creationDate;
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private List<Event> events;
    @Getter
    @Setter
    private GeoPosition geoPosition;

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
}
