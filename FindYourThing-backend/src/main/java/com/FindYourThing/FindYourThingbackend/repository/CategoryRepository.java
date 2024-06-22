package com.FindYourThing.FindYourThingbackend.repository;

import com.FindYourThing.FindYourThingbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query
    List<Category> findCategoriesByTitle(String title);
}
