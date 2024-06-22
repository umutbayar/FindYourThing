package com.FindYourThing.FindYourThingbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue()
    private Long id;
    private String title;
    private String Summary;
    private String Poster;
    private String Length;

}
