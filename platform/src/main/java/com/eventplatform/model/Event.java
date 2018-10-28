package com.eventplatform.model;

import java.math.BigInteger;
import java.util.Date;

public class Event extends Entity {
    public Event(BigInteger id, Date creationDate) {
        super(id, creationDate);
    }
}
