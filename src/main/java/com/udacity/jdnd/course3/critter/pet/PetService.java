package com.udacity.jdnd.course3.critter.pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class PetService {
    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;
    public PetService(PetRepository petRepository,
                      CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }
    public Pet savePet(Pet pet, Long ownerId) {
        Customer owner = customerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        pet.setOwner(owner);
        owner.getPets().add(pet);
        return petRepository.save(pet);
    }
    public Pet getPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }
}
