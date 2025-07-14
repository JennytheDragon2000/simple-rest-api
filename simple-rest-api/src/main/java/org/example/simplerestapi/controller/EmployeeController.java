package org.example.simplerestapi.controller;

import org.example.simplerestapi.model.Employee;
import org.example.simplerestapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Employee>> getActiveEmployees() {
        List<Employee> employees = employeeService.getActiveEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{networkId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String networkId) {
        Optional<Employee> employee = employeeService.getEmployeeById(networkId);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Employee>> getEmployeesByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Employee> employees = employeeService.getEmployeesByDate(localDate);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/by-date-and-shift")
    public ResponseEntity<List<Employee>> getEmployeesByDateAndShift(
            @RequestParam String date,
            @RequestParam String shiftId) {
        LocalDate localDate = LocalDate.parse(date);
        List<Employee> employees = employeeService.getEmployeesByDateAndShift(localDate, shiftId);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{networkId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable String networkId,
            @Valid @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(networkId, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{networkId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String networkId) {
        employeeService.deleteEmployee(networkId);
        return ResponseEntity.noContent().build();
    }
}

