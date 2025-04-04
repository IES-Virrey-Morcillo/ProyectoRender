package org.crudrest.service;

import org.crudrest.entity.Pet;
import org.crudrest.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Service->ServiceImpl->Controllers
@Service
public class PetServiceImpl implements PetService{

    private PetRepository repository;

    public PetServiceImpl(PetRepository repository) {this.repository = repository;}

    @Override
    public Optional<Pet> findById(Long id) {
        Objects.requireNonNull(id);
        return repository.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Pet> findByNameAndSpecie(String name, String specie) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(specie);
        return repository.findByNameAndSpecie(name, specie);
    }

    @Override
    public List<Pet> findByNameContainingOrSpecieContaining(String name, String specie) {
        return repository.findByNameContainingOrSpecieContaining(name, specie);
    }


    @Override
    public List<Pet> findByAgeLessThan(int age) {
        return repository.findByAgeLessThan(age);
    }

    @Override
    public void save(Pet pet) {
        Objects.requireNonNull(pet);
        this.repository.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id);
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
