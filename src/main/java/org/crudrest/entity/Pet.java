package org.crudrest.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_chip", nullable = false, unique = true)
    @Schema(example = "1", description = "Identificador clave primaria de la mascota, de tipo Long")
    private Long numChip;
    @Column(nullable = false)
    @Schema(example = "Max", description = "Nombre de la mascota, de tipo String")
    private String name;
    @Schema(example = "Perro", description = "Especie de la mascota, de tipo String")
    private String specie;
    @Schema(example = "4", description = "Edad de la mascota, de tipo int")
    private int age;
    @Schema(example = "https://hospitalveterinariodonostia.com/wp-content/uploads/2022/02/Personalidad-gatos-674x337.png", description = "URL de la imagen de la mascota, de tipo String")
    private String image;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @Schema(example = "Carlos (JSON entero)", description = "Propietario de la mascota, de tipo Owner")
    private Owner owner;

    public Pet() {}
    public Pet(Long numChip ,String name, String specie, int age, String image){
        this.numChip = numChip;
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.image = image;
    }
    public Pet(String name, String specie, int age, String image){
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.image = image;
    }

    public Long getNumChip() {
        return numChip;
    }

    public void setNumChip(Long numChip) {
        this.numChip = numChip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "numChip=" + numChip +
                ", name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", age=" + age +
                ", image='" + image + '\'' +
                '}';
    }
}