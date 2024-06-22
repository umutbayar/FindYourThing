package com.FindYourThing.FindYourThingbackend.dto;

import com.FindYourThing.FindYourThingbackend.model.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String Summary;
    private String Poster;
    private String Length;
    private List<Category> categories = new ArrayList<>();
}
