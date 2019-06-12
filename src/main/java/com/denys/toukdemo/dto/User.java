package com.denys.toukdemo.dto;

import java.util.List;

public class User {

    private String name;
    private String surname;
    private String email;
    private List<Seans> seanses;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(List<Seans> seanses) {
        this.seanses = seanses;
    }
}
