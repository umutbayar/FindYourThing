package com.FindYourThing.FindYourThingbackend.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id) {
        super("Could not find the category with id: " + id);
    }

}
