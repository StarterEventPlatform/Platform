package com.eventplatform.model;

import java.util.Date;

public class Entity {
    private int id;
    private Date creationDate;

    public Entity(int id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
