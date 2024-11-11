package com.adoptmate.repositories;

import com.adoptmate.models.IncomeStatement;
import com.adoptmate.models.Pet;
import com.adoptmate.models.Shelter;
import com.adoptmate.models.User;
import com.adoptmate.tools.SQLConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(SQLConfiguration.QUERIES.USER.FIND_BY_NAME)
    List<User> findByName(String name);
    @Query(SQLConfiguration.QUERIES.USER.STATEMENTS)
    List<IncomeStatement> findByStatements(long id);
    @Query(SQLConfiguration.QUERIES.USER.MATCHED_PETS)
    List<Pet> findByMatchedPets(long id);
    @Query(SQLConfiguration.QUERIES.USER.LIKED_SHELTERS)
    List<Shelter> findLikedShelters(long id);
    @Query(SQLConfiguration.QUERIES.USER.LOGIN)
    User login(String name, byte[] password);
}
