package com.FindYourThing.FindYourThingbackend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String address;
}
