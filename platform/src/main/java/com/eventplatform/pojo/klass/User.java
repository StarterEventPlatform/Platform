package com.eventplatform.pojo.klass;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date creationDate;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String role;

}
