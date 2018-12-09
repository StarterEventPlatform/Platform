package com.eventplatform.pojo.klass;

import com.eventplatform.pojo.CollectionConstants;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = CollectionConstants.COLLECTION_NAME_USER)
public class User {

    @Id
    private int id;
    @Field(value = "Creation_Date")
    private Date creationDate;
    @Field(value = "Name")
    private String name;
    @Field(value = "Surname")
    private String surname;
    @Field(value = "Login")
    private String login;
    @Field(value = "Email")
    private String email;
    @Field(value = "Password")
    private String password;
    @Field(value = "Role")
    private String role;

}
