package com.eventplatform.domain.model;

import com.eventplatform.domain.MongoMappingConstants;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = MongoMappingConstants.COLLECTION_NAME_GEOPOSITION)
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
