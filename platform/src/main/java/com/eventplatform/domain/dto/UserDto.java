package com.eventplatform.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private int id;
    private Date creationDate;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String role;

}
