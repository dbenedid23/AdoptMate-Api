package com.adoptmate.repositories;

import com.adoptmate.enumerations.Specie;
import com.adoptmate.models.Breed;
import com.adoptmate.tools.SQLConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    List<Breed> findBySpecie(Specie specie);
    @Query(SQLConfiguration.QUERIES.BREED.FIND_BY_NAME)
    List<Breed> findByNameLike(String name);
    Breed findByName(String name);
}
