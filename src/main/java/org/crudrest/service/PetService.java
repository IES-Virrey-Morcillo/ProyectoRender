package org.crudrest.service;

import org.crudrest.entity.Pet;

import java.util.List;
import java.util.Optional;

//Repository->Service->ServiceImpl
public interface PetService{
    // RETRIEVE
    Optional<Pet> findById(Long id);
    List<Pet> findAll();
        //Definidos en PetRepository
        List<Pet> findByNameAndSpecie(String name, String specie);
        List<Pet> findByNameContainingOrSpecieContaining(String name, String specie);
        List<Pet> findByAgeLessThan(int age);

    // CREATE - UPDATE
    void save(Pet pet);

    //DELETE
    void deleteById(Long id);
    void deleteAll();
}
