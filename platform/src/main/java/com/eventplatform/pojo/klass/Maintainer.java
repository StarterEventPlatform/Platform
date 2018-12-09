package com.eventplatform.pojo.klass;

import com.eventplatform.pojo.CollectionConstants;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = CollectionConstants.COLLECTION_NAME_MAINTAINER)
public class Maintainer {

    @Id
    private int id;
    @Field(value = "Creation_Date")
    private Date creationDate;
    @DBRef
    @Field(value = "User")
    private User user;
    @Field(value = "Name")
    private String name;
    @Field(value = "Description")
    private String description;
    @DBRef
    @Field(value = "GeoPosition")
    private GeoPosition geoPosition;

    /*
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
    */
}
