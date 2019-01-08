package com.eventplatform.domain.model;

import com.eventplatform.domain.MongoMappingConstants;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ToString
@EqualsAndHashCode
@Data
@Document(collection = MongoMappingConstants.COLLECTION_NAME_EVENT)
public class Event {

    @Id
    private int id;
    @Field(value = "Creation_Date")
    private Date creationDate;
    @Field(value = "Name")
    private String name;
    @Field(value = "Description")
    private String description;
    @DBRef
    @Field(value = "GeoPosition")
    private GeoPosition geoPosition;
    @DBRef
    @Field(value = "Maintainers")
    private List<Maintainer> maintainers;
    @Field(value = "Type")
    private String type;
    @Field(value = "Event_Date")
    private Date eventDate;

    public Event(int id, Date creationDate, String name, String description, GeoPosition geoPosition, Maintainer maintainers, String type, Date eventDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.description = description;
        this.geoPosition = geoPosition;
        addMaintainer(maintainers);
        this.type = type;
        this.eventDate = eventDate;
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
}
