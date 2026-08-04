package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final CustomerRepository customerRepo;
    private final EmployeeRepository employeeRepo;
    private final PetRepository petRepo;

    public UserService(CustomerRepository customerRepo,
                       EmployeeRepository employeeRepo,
                       PetRepository petRepo) {
        this.customerRepo = customerRepo;
        this.employeeRepo = employeeRepo;
        this.petRepo = petRepo;
    }

    // ---------- CUSTOMER ----------
    public Customer saveCustomer(Customer c) {
        return customerRepo.save(c);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getOwnerByPet(long petId) {
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        return pet.getOwner();
    }

    // ---------- EMPLOYEE ----------
    public Employee saveEmployee(Employee e) {
        return employeeRepo.save(e);
    }

    public Employee getEmployee(long id) {
        return employeeRepo.findById(id).orElseThrow();
    }

    public void setAvailability(Set<DayOfWeek> days, long employeeId) {
        Employee e = getEmployee(employeeId);
        e.setDaysAvailable(days);
        employeeRepo.save(e);
    }

    public List<Employee> findEmployeesForService(
            Set<EmployeeSkill> skills, LocalDate date) {

        DayOfWeek day = date.getDayOfWeek();

        return employeeRepo.findAll().stream()
                .filter(e -> e.getDaysAvailable() != null &&
                        e.getDaysAvailable().contains(day))
                .filter(e -> e.getSkills() != null &&
                        e.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
