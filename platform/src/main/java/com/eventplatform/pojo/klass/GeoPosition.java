package com.eventplatform.pojo.klass;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "GeoPosition")
public class GeoPosition {

    @Id
    private int id;
    @Field(value = "Creation_Date")
    private Date creationDate;
    @Field(value = "Latitude")
    private float latitude;
    @Field(value = "Longitude")
    private float longitude;

}
