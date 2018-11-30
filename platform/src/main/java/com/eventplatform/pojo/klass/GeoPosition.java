package com.eventplatform.pojo.klass;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class GeoPosition {

    @Id
    @GeneratedValue
    private int id;
    private Date creationDate;
    private float latitude;
    private float longitude;

}
