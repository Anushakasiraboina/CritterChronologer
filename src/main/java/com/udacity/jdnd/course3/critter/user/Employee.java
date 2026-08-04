package com.udacity.jdnd.course3.critter.user;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "skill")
    private Set<EmployeeSkill> skills = new HashSet<>();
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;
    public Long getId() {
        return id;
    }
    public String getName(){ return name; }
    public Set<EmployeeSkill> getSkills() { return skills; }
    public Set<DayOfWeek> getDaysAvailable() { return daysAvailable; }
    public void setName(String name) { this.name = name; }
    public void setSkills(Set<EmployeeSkill> skills) { this.skills = skills; }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }




}
