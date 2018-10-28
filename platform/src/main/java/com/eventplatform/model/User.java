package com.eventplatform.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

// todo look at https://github.com/StarterEventPlatform/Platform/issues/4
// private String role???
public class User extends Entity {

    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;

    public User(BigInteger id, Date creationDate) {
        super(id, creationDate);
    }

    public User(BigInteger id, Date creationDate, String name, String surname, String login, String email, String password) {
        super(id, creationDate);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getLogin(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + super.getCreationDate() +
                '}';
    }
}
