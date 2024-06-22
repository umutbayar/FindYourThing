package com.FindYourThing.FindYourThingbackend.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String username) {
        super("Username '" + username + "' already in use!");
    }
}
