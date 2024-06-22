package com.FindYourThing.FindYourThingbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue()
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;
}
