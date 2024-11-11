package com.adoptmate.repositories;

import com.adoptmate.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Shelter findByName(String name);
}
