package com.eventplatform.model;

import java.util.Date;

public class GeoPosition extends Entity {
    public GeoPosition(int id, Date creationDate) {
        super(id, creationDate);
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "id='" + super.getId() + '\'' +
                ", creationDate=" + super.getCreationDate() +
                '}';
    }
}
