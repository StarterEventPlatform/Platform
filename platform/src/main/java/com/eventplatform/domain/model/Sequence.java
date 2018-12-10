package com.eventplatform.domain.model;

import com.eventplatform.domain.MongoMappingConstants;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@Document(collection = MongoMappingConstants.COLLECTION_NAME_SEQUENCE)
public class Sequence {

    @Id
    private String id;
    @Field(value = "sequence")
    private int sequence;
}
