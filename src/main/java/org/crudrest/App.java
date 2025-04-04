package org.crudrest;

import org.crudrest.entity.Owner;
import org.crudrest.entity.Pet;
import org.crudrest.repository.OwnerRepository;
import org.crudrest.repository.PetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        // Repositorios
        OwnerRepository ownerRepository = context.getBean(OwnerRepository.class);
        PetRepository petRepository = context.getBean(PetRepository.class);

        // Creación de dueños
        List<Owner> ownerList = List.of(
                new Owner("Carlos", "666666661", LocalDate.of(1990,1,15)),
                new Owner("Laura", "666666662", LocalDate.of(1985,2,25)),
                new Owner("Miguel", "666666663", LocalDate.of(2000,3,10)),
                new Owner("Ana", "666666664", LocalDate.of(1998,7,5)),
                new Owner("Pedro", "666666665", LocalDate.of(1995,11,20)),
                new Owner("Lucía", "666666666", LocalDate.of(2003,6,30)),
                new Owner("Santiago", "666666667", LocalDate.of(1987,9,18)),
                new Owner("Elena", "666666668", LocalDate.of(2001,4,22))
        );

        // Creación de mascotas
        List<Pet> petList = List.of(
                new Pet("Max", "Perro", 3, "https://content.elmueble.com/medio/2023/05/25/cachorro-de-bichon-maltes_61209cca_230525143331_1000x1500.jpg"),
                new Pet("Luna", "Gato", 2, "https://hospitalveterinariodonostia.com/wp-content/uploads/2022/02/Personalidad-gatos-674x337.png"),
                new Pet("Rocky", "Perro", 4, "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTQ1bp6fCYXztC-oJQyDwj17haS6t48eIHcxFAaad5o4dhCv4F5pACG82_HaRk1lW0-p5zXsLWGXnzVT9i_rzcbmh28ioXOcLEZHgitgA"),
                new Pet("Nina", "Gato", 1, "https://hospitalveterinariodonostia.com/wp-content/uploads/2020/10/gatos-854x427.png"),
                new Pet("Toby", "Conejo", 5, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Oryctolagus_cuniculus_Tasmania_2.jpg/250px-Oryctolagus_cuniculus_Tasmania_2.jpg"),
                new Pet("Simba", "Perro", 6, "https://media.istockphoto.com/id/1143335963/es/foto/perro-de-raza-chow-chow.jpg?s=612x612&w=0&k=20&c=mvv8aPSVUFigs5mKfRi_O-I0U1VLAPBZ_PJcNXblwyk="),
                new Pet("Coco", "Loro", 8, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Amazona_auropalliata_-Roatan_Tropical_Butterfly_Garden-8a.jpg/250px-Amazona_auropalliata_-Roatan_Tropical_Butterfly_Garden-8a.jpg"),
                new Pet("Daisy", "Tortuga", 10, "https://www.anipedia.net/imagenes/tortuga-mediterranea.jpg"),
                new Pet("Thor", "Hamster", 2, "https://hvc.cat/wp-content/uploads/2019/06/ha%CC%80mster-1-1080x675.jpg"),
                new Pet("Bella", "Hurón", 4, "https://www.hogarmania.com/archivos/202207/huron-domestico-848x477x80xX.jpg")
        );

        // Almacenamiento de información
        ownerRepository.saveAll(ownerList);
        petList = petRepository.saveAll(petList);

        // Asignación de mascotas a dueños
        for (int i = 0; i < Math.min(ownerList.size(), petList.size()); i++) {
            petList.get(i).setOwner(ownerList.get(i));
        }

        petRepository.saveAll(petList);
    }
}