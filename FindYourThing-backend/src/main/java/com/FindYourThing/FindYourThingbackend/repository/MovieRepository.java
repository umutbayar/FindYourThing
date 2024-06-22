package com.FindYourThing.FindYourThingbackend.repository;

import com.FindYourThing.FindYourThingbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
