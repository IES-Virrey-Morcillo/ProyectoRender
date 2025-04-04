package org.crudrest.repository;

import org.crudrest.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
    List<Owner> findByDateOfBirthAfter(LocalDate dateOfBirth);
    List<Owner> findByFirstNameContainingOrPhoneContaining(String firstName, String phone);
}
