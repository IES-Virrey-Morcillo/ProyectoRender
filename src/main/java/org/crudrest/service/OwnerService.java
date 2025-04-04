package org.crudrest.service;

import org.crudrest.entity.Owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Repository->Service->ServiceImpl
public interface OwnerService {
    // RETRIEVE
    Optional<Owner> findById(UUID id);
    List<Owner> findAll();
    //Definidos en OwnerRepository
    List<Owner> findByDateOfBirthAfter(LocalDate dateOfBirth);
    List<Owner> findByFirstNameContainingOrPhoneContaining(String firstName, String phone);

    // CREATE - UPDATE
    Owner save(Owner owner);

    //DELETE
    void deleteById(UUID id);
    void deleteAll();
}
