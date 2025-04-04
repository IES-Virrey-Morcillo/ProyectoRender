package org.crudrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.crudrest.entity.Pet;
import org.crudrest.service.PetServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetRestController {

    private final PetServiceImpl service;

    public PetRestController(PetServiceImpl service) {
        this.service = service;
    }

    /*
     * GET http://localhost:8080/pet/pets
     * */
    @GetMapping("/pets")
    @Operation(summary = "Obtener todas las mascotas", description = "Devuelve una lista de todas las mascotas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de mascotas recuperada con éxito", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<List<Pet>> findAll() {
        List<Pet> pets = service.findAll();
        if (pets.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(pets);
    }

    /*
     * GET http://localhost:8080/pet/pets/{numChip}
     * */
    @GetMapping("/pets/{numChip}")
    @Operation(summary = "Obtener una mascota por el número del chip.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mascota encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Pet> findById(@PathVariable Long numChip) {
        Optional<Pet> pet = service.findById(numChip);
        if (pet.isPresent())
            return ResponseEntity.ok(pet.get());
        else
            return ResponseEntity.notFound().build();
    }

    /*
     * POST http://localhost:8080/pet/pets
     * */
    @PostMapping("/pets")
    @Operation(summary = "Crear mascota nueva.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha podido crear la mascota correctamente.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        // VALIDACIONES...
        if (pet.getNumChip() != null)
            return ResponseEntity.badRequest().build();
        this.service.save(pet);
        return ResponseEntity.ok(pet);
    }

    /*
     * PUT http://localhost:8080/pet/pets
     * */
    @PutMapping("/pets")
    @Operation(summary = "Actualizar una mascota existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha podido actualizar la mascota correctamente.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {
        // VALIDACIONES...
        this.service.save(pet);
        return ResponseEntity.ok(pet);
    }

    /*
     * DELETE http://localhost:8080/pet/pets/{numChip}
     * */
    @DeleteMapping("/pets/{numChip}")
    @Operation(summary = "Elimina una mascota a través del numero del chip")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha podido eliminar la mascota correctamente.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Void> delete(@PathVariable Long numChip) {
        this.service.deleteById(numChip);
        return ResponseEntity.noContent().build();
    }

    /*
     * DELETE http://localhost:8080/pet/pets
     * */
    @DeleteMapping("/pets")
    @Operation(summary = "Eliminar todas las mascotas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se han podido eliminar todas las mascotas correctamente.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Void> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // Otras consultas
    // GET http://localhost:8080/pet/pets/byNameAndSpecie?name=Mascota6&specie=Especie6
    @GetMapping("/pets/byNameAndSpecie")
    @Operation(summary = "Busca una mascota a través del nombre y su especie.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha encontrado la mascota.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<List<Pet>> findByNameAndSpecie(
            @RequestParam("name") String name,
            @RequestParam("specie") String specie) {
        List<Pet> pets = service.findByNameAndSpecie(name, specie);
        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pets);
        }
    }

    // GET http://localhost:8080/pet/pets/byAgeLessThan?age=5
    @GetMapping("/pets/byAgeLessThan")
    @Operation(summary = "Busca una mascota a través una edad máxima.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado la mascota.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<List<Pet>> findByAgeLessThan(@RequestParam("age") int age) {
        List<Pet> pets = service.findByAgeLessThan(age);
        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pets);
        }
    }
}
