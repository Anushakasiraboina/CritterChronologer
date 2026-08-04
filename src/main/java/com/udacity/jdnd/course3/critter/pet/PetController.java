package com.udacity.jdnd.course3.critter.pet;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    public PetController(PetService petService) {
        this.petService = petService;
    }
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertDTOToPet(petDTO);
        Pet savedPet = petService.savePet(pet, petDTO.getOwnerId());
        return convertPetToDTO(savedPet);
    }
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToDTO(petService.getPet(petId));
    }
    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getAllPets().stream()
                .map(this::convertPetToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream()
                .map(this::convertPetToDTO)
                .collect(Collectors.toList());
    }
    private Pet convertDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        return pet;
    }
    private PetDTO convertPetToDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setType(pet.getType());
        dto.setBirthDate(pet.getBirthDate());
        dto.setNotes(pet.getNotes());
        if (pet.getOwner() != null) {
            dto.setOwnerId(pet.getOwner().getId());
        }
        return dto;
    }
}
