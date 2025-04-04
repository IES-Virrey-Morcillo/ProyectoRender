package org.crudrest.controller;

import org.crudrest.entity.Owner;
import org.crudrest.entity.Pet;
import org.crudrest.service.OwnerServiceImpl;
import org.crudrest.service.PetServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/pets")
public class PetController {

    private PetServiceImpl service;
    private OwnerServiceImpl ownerService;

    public PetController(PetServiceImpl service, OwnerServiceImpl ownerService) {
        this.service = service;
        this.ownerService = ownerService;
    }

    /*
     * GET http://localhost:8080/pets
     * */
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("pets", service.findAll());
        return "pet-list";
    }

    /*
     * GET http://localhost:8080/pets/{numChip}/view
     * */
    @GetMapping("/{numChip}/view")
    public String findById( @PathVariable Long numChip, Model model, RedirectAttributes redirectAttributes) {
        Optional<Pet> pet = service.findById(numChip);

        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            return "pet-view"; // Nombre del archivo HTML en templates
        } else {
            redirectAttributes.addFlashAttribute("message", "Mascota no encontrada");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/pets";
        }
    }

    /*
    GET http://localhost:8080/pets/new
     */
    @GetMapping("/new")
    public String getForm(Model model) {
        model.addAttribute("pet",new Pet());
        model.addAttribute("owners", ownerService.findAll());
        return "pet-form";
    }

    /*
    POST http://localhost:8080/pets/new/////////////////////////////////////ARREGLARR
     */
    @PostMapping("/new")
    public String save(@ModelAttribute("pet") Pet pet) {
        this.service.save(pet);
        return "redirect:/pets";
    }


    /*
    * GET http://localhost:8080/pets/{numChip}/edit
    * */
    @GetMapping("/{numChip}/edit")
    public String update(@PathVariable Long numChip, Model model, RedirectAttributes redirectAttributes) {
        Optional<Pet> pet = service.findById(numChip);

        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            model.addAttribute("owners", ownerService.findAll());
            return "pet-edit"; // Nombre del archivo HTML en templates
        } else {
            redirectAttributes.addFlashAttribute("message", "Mascota no encontrada");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/pets";
        }
    }

    /*
     * POST http://localhost:8080/pets/{numChip}/edit
     * */
    @PostMapping("/{numChip}/edit")
    public String update(@ModelAttribute("pet") Pet pet, RedirectAttributes redirectAttributes){
        this.service.save(pet);
        redirectAttributes.addFlashAttribute("message", "Mascota modificado con éxito");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/pets";
    }

    /*
     * GET http://localhost:8080/pets/{numChip}/delete
     * */
    @GetMapping("/{numChip}/delete")
    public String deletePet(@PathVariable Long numChip, Model model, RedirectAttributes redirectAttributes){
        Optional<Pet> pet = service.findById(numChip);

        if (pet.isPresent()) {
            model.addAttribute("pet", pet.get());
            return "pet-delete";
        } else {
            redirectAttributes.addFlashAttribute("message", "Mascota no encontrada");
            redirectAttributes.addFlashAttribute("alert", "warning");
            return "redirect:/pets";
        }
    }

    /*
     * POST http://localhost:8080/pets/{numChip}/delete
     * */
    @PostMapping("/{NumChip}/delete")
    public String delete(@ModelAttribute("pet") Pet pet, RedirectAttributes redirectAttributes){
        this.service.deleteById(pet.getNumChip());
        redirectAttributes.addFlashAttribute("message", "Mascota eliminada con éxito");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/pets";
    }

    /*
     * GET http://localhost:8080/pets/deleteAll
     * */
    @GetMapping("/deleteAll")
    public String deleteAll(Model model) {
        int nPets = service.findAll().size();
        model.addAttribute("nPets", nPets);
        return "pet-deleteAll";
    }

    /*
     * POST http://localhost:8080/pets/deleteAll
     * */
    @PostMapping("/deleteAll")
    public String deleteAllPets(RedirectAttributes redirectAttributes){
        this.service.deleteAll();
        redirectAttributes.addFlashAttribute("message", "Se han eliminado las mascotas");
        redirectAttributes.addFlashAttribute("alert", "success");
        return "redirect:/pets";
    }

    /*
    * GET http://localhost:8080/pets/seach
     */
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,Model model){
        List<Pet> pets = this.service.findByNameContainingOrSpecieContaining(keyword,keyword);
        model.addAttribute("pets", pets);
        return "pet-list";
    }

    @GetMapping("/filterByAge/{age}")
    public String filter(@RequestParam("keyword") String keyword,Model model){
        return search(keyword,model);
    }
}
