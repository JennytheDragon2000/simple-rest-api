package org.example.simplerestapi.service;

import org.example.simplerestapi.model.Employee;
import org.example.simplerestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getActiveEmployees() {
        return employeeRepository.findByActive(true);
    }

    public Optional<Employee> getEmployeeById(String networkId) {
        return employeeRepository.findById(networkId);
    }

    public List<Employee> getEmployeesByDate(LocalDate date) {
        return employeeRepository.findEmployeesByDate(date);
    }

    public List<Employee> getEmployeesByDateAndShift(LocalDate date, String shiftId) {
        return employeeRepository.findEmployeesByDateAndShift(date, shiftId);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String networkId) {
        employeeRepository.deleteById(networkId);
    }

    public Employee updateEmployee(String networkId, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(networkId);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setCountry(updatedEmployee.getCountry());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setActive(updatedEmployee.getActive());
            return employeeRepository.save(employee);
        }
        return null;
    }
}
