package com.eventplatform.pojo.klass;

import com.eventplatform.pojo.CollectionConstants;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@Document(collection = CollectionConstants.COLLECTION_NAME_SEQUENCE)
public class Sequence {

    @Id
    private String id;
    @Field(value = "sequence")
    private int sequence;
}
