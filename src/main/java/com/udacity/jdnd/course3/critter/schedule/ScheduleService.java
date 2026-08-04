package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return scheduleRepository.findAllByEmployeesContains(employee);
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return scheduleRepository.findAllByPetsContains(pet);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        // get all pets for this customer
        List<Pet> pets = petRepository.findAll().stream()
                .filter(p -> p.getOwner().getId().equals(customerId))
                .collect(Collectors.toList());
        // collect all schedules that contain any of these pets
        return scheduleRepository.findAll().stream()
                .filter(s -> s.getPets().stream().anyMatch(pets::contains))
                .collect(Collectors.toList());
    }
}
