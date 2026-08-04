package com.udacity.jdnd.course3.critter.user;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ---------- CUSTOMER ----------
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setNotes(dto.getNotes());

        Customer saved = service.saveCustomer(customer);

        CustomerDTO result = new CustomerDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setPhoneNumber(saved.getPhoneNumber());
        result.setNotes(saved.getNotes());
        result.setPetIds(List.of()); // initially empty

        return result;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return service.getAllCustomers().stream().map(c -> {
            CustomerDTO dto = new CustomerDTO();
            BeanUtils.copyProperties(c, dto);
            if (c.getPets() != null) {
                dto.setPetIds(
                        c.getPets().stream().map(Pet::getId).collect(Collectors.toList())
                );
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer c = service.getOwnerByPet(petId);
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(c, dto);
        dto.setPetIds(
                c.getPets().stream().map(Pet::getId).collect(Collectors.toList())
        );
        return dto;
    }

    // ---------- EMPLOYEE ----------
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO dto) {
        Employee e = new Employee();
        e.setName(dto.getName());
        e.setSkills(dto.getSkills());
        e.setDaysAvailable(dto.getDaysAvailable());

        Employee saved = service.saveEmployee(e);

        EmployeeDTO result = new EmployeeDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setSkills(saved.getSkills());
        result.setDaysAvailable(saved.getDaysAvailable());

        return result;

    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee e = service.getEmployee(employeeId);
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }

    @PutMapping("/employee/{employeeId}/availability")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable,
                                @PathVariable long employeeId) {
        service.setAvailability(daysAvailable, employeeId);
    }

    @PostMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO request) {
        return service.findEmployeesForService(request.getSkills(), request.getDate())
                .stream()
                .map(e -> {
                    EmployeeDTO dto = new EmployeeDTO();
                    BeanUtils.copyProperties(e, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }


}
