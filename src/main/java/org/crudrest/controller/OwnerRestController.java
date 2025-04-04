package org.crudrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.crudrest.entity.Owner;
import org.crudrest.service.OwnerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/owner")
public class OwnerRestController {

    OwnerServiceImpl service;

    public OwnerRestController(OwnerServiceImpl service) {this.service = service;}
    /*
     * GET http://localhost:8080/owner/owners
     * */
    @GetMapping("/owners")
    @Operation(summary = "Muestra una lista de todos los dueños de mascotas registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha encontrado el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<List<Owner>> findAll() {
        List<Owner> owners = service.findAll();
        if(owners.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(owners);
    }
    /*
     * GET http://localhost:8080/owner/owners/{id}
     * */
    @GetMapping("/owners/{id}")
    @Operation(summary = "Busca un dueño través de su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha encontrado el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Dueño no encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Owner> findById(@PathVariable UUID id) {
        Optional<Owner> owner=service.findById(id);
        if(owner.isPresent())
            return ResponseEntity.ok(owner.get());
        else
            return ResponseEntity.notFound().build();
    }

    /*
     * POST http://localhost:8080/owner/owners
     * */
    @PostMapping("/owners")
    @Operation(summary = "Registra un nuevo dueño.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha creado el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "No se pudo crear el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
    })
    public ResponseEntity<Owner> create(@RequestBody Owner owner) {
        //VALIDACIONES...
        if(owner.getId() != null)
            return ResponseEntity.badRequest().build();
        this.service.save(owner);
        return ResponseEntity.ok(owner);
    }

    /*
     * PUT http://localhost:8080/owner/owners
     * */
    @PutMapping("/owners")
    @Operation(summary = "Actualiza un dueño existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha modificado el dueño.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "No se pudo encontrar/modificar el dueño.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
    })
    public ResponseEntity<Owner> update(@RequestBody Owner owner) {
        this.service.save(owner);
        return ResponseEntity.ok(owner);
    }

    /*
     * DELETE http://localhost:8080/owners/owners/{identifier}
     * */
    @DeleteMapping("/owners/{identifier}")
    @Operation(summary = "Elimina un dueño existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha eliminado el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "No se pudo encontrar/eliminar el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
    })
    public ResponseEntity<Owner> delete(@PathVariable("identifier") UUID id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/owners")
    @Operation(summary = "Elimina todos los dueños registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se han eliminado todos los registros", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<Owner> deleteAll() {
        this.service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    //Otras consultas
    //Filtros
    //http://localhost:8080/owner/owners/byDateOfBirthAfter?date=2000-01-01
    @GetMapping("/owners/byDateOfBirthAfter")
    @Operation(summary = "Busca un dueño través de su fecha de cumpleaños.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha encontrado el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Dueño no encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<List<Owner>> findByDateOfBirthAfter(@RequestParam("date") LocalDate date) {
        List<Owner> owners = service.findByDateOfBirthAfter(date);
        if (owners.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(owners);
        }
    }

    //http://localhost:8080/owner/owners/byFirstNameOrPhone?input=Mig
    @GetMapping("/owners/byFirstNameOrPhone")
    @Operation(summary = "Busca un dueño través del nombre o su teléfono.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se ha encontrado el dueño.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Dueño no encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<List<Owner>> findByFirstNameContainingOrPhoneContaining(@RequestParam("input") String input) {
        List<Owner> owners = service.findByFirstNameContainingOrPhoneContaining(input, input);
        if (owners.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(owners);
        }
    }
}