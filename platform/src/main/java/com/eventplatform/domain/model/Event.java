package com.eventplatform.domain.model;

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
public class Event {

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
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private GeoPosition geoPosition;
    private List<Maintainer> maintainers;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private Date eventDate;

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
