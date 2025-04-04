package org.crudrest.service;

import org.crudrest.entity.Owner;
import org.crudrest.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerServiceImpl implements OwnerService{

    private OwnerRepository repository;

    public OwnerServiceImpl(OwnerRepository repository) {this.repository = repository;}

    @Override
    public Optional<Owner> findById(UUID id) {
        Objects.requireNonNull(id);
        return this.repository.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Owner> findByDateOfBirthAfter(LocalDate dateOfBirth) {
        Objects.requireNonNull(dateOfBirth);
        return this.repository.findByDateOfBirthAfter(dateOfBirth);
    }

    @Override
    public List<Owner> findByFirstNameContainingOrPhoneContaining(String firstName, String phone) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(phone);
        return this.repository.findByFirstNameContainingOrPhoneContaining(firstName, phone);
    }

    @Override
    public Owner save(Owner owner) {
        Objects.requireNonNull(owner);
        return this.repository.save(owner);
    }

    @Override
    public void deleteById(UUID id) {
        Objects.requireNonNull(id);
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {this.repository.deleteAll();}
}
