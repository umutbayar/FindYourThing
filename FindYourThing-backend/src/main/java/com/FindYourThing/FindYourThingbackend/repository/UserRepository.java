package com.FindYourThing.FindYourThingbackend.repository;

import com.FindYourThing.FindYourThingbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query
    boolean existsByUsername(String username);

}
