package com.udacity.jdnd.course3.critter.pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized
    private String name;
    @Nationalized
    private String notes;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private PetType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Customer owner;
    @ManyToMany(mappedBy = "pets")
    private List<Schedule> schedules;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public PetType getType() {
        return type;
    }
    public void setType(PetType type) {
        this.type = type;
    }
    public Customer getOwner() {
        return owner;
    }
    public void setOwner(Customer owner) {
        this.owner = owner;
    }
    public List<Schedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
