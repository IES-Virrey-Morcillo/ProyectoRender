package org.crudrest.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(example = "234456f4-cfef-4e7f-899f-8b6e33df397e", description = "Identificador clave primaria del dueño UUID")
    private UUID id;
    @Column(nullable = false)
    @Schema(example = "Carlos", description = "Nombre del dueño, de tipo String")
    private String firstName;
    @Column(unique = true, nullable = false)
    @Schema(example = "666666661", description = "Teléfono del dueño, de tipo String")
    private String phone;
    @Column(nullable = false)
    @Schema(example = "LocalDate.now()", description = "Fecha de nacimiento del dueño, de tipo LocalDate")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Schema(description = "Lista de mascotas del dueño, de tipo Set<Pet>")
    private Set<Pet> pets = new HashSet<>();

    public Owner() {}
    public Owner( String firstName, String phone, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
