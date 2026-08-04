package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import java.util.List;
import java.util.ArrayList;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized
    private String name;
    private String phoneNumber;
    @Nationalized
    private String notes;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getNotes() {
        return notes;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
