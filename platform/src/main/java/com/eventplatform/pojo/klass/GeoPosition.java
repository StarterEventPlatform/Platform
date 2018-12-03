package com.eventplatform.pojo.klass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class GeoPosition {

    @Id
    @GeneratedValue
    private int id;
    @Getter
    @Setter
    private Date creationDate;
    @Getter
    @Setter
    private float latitude;
    @Getter
    @Setter
    private float longitude;

}
