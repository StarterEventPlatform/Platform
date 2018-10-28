package com.eventplatform.model;

import java.math.BigInteger;
import java.util.Date;

public class Entity {
    private BigInteger id;
    private Date creationDate;

    public Entity(BigInteger id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
