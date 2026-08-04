package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;

    public ScheduleController(ScheduleService scheduleService,
                              PetRepository petRepository,
                              EmployeeRepository employeeRepository) {
        this.scheduleService = scheduleService;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        // Employees
        List<Employee> employees = scheduleDTO.getEmployeeIds()
                .stream()
                .map(id -> employeeRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());
        schedule.setEmployees(employees);

        // Pets
        List<Pet> pets = scheduleDTO.getPetIds()
                .stream()
                .map(id -> petRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());
        schedule.setPets(pets);

        Schedule saved = scheduleService.saveSchedule(schedule);
        return convertToDTO(saved);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleForPet(petId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDate(schedule.getDate());
        dto.setActivities(schedule.getActivities());
        dto.setEmployeeIds(
                schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList())
        );
        dto.setPetIds(
                schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList())
        );
        return dto;
    }
}
