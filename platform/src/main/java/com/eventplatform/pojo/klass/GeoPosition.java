package com.eventplatform.pojo.klass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Document(collection = "GeoPosition")
public class GeoPosition {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    @Field(value = "Creation_Date")
    private Date creationDate;
    @Setter
    @Getter
    @Field(value = "Latitude")
    private float latitude;
    @Setter
    @Getter
    @Field(value = "Longitude")
    private float longitude;

}
