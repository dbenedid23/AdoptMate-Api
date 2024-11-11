package com.adoptmate.repositories;

import com.adoptmate.enumerations.Specie;
import com.adoptmate.tools.SQLConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adoptmate.models.Pet;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(SQLConfiguration.QUERIES.PET.FIND_BY_SHELTER)
    List<Pet> findByShelter(String shelter);
    @Query(SQLConfiguration.QUERIES.PET.FIND_BY_SPECIE)
    List<Pet> findBySpecie(Specie specie);
    @Query(SQLConfiguration.QUERIES.PET.FIND_BY_TYPE)
    List<Pet> findByType(String type);
}
