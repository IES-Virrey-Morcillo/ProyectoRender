package org.crudrest.repository;

import org.crudrest.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByNameAndSpecie(String name, String specie);
    List<Pet> findByNameContainingOrSpecieContaining(String name, String specie);
    List<Pet> findByAgeLessThan(int age);
}
