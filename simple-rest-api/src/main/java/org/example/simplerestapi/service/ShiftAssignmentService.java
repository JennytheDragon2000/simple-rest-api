package org.example.simplerestapi.service;

import org.example.simplerestapi.dto.ShiftAssignmentRequest;
import org.example.simplerestapi.model.ShiftAssignment;
import org.example.simplerestapi.model.Employee;
import org.example.simplerestapi.model.ShiftType;
import org.example.simplerestapi.repository.ShiftAssignmentRepository;
import org.example.simplerestapi.repository.EmployeeRepository;
import org.example.simplerestapi.repository.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftAssignmentService {

    @Autowired
    private ShiftAssignmentRepository shiftAssignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftTypeRepository shiftTypeRepository;

    public List<ShiftAssignment> getAllShiftAssignments() {
        return shiftAssignmentRepository.findAll();
    }

    public List<ShiftAssignment> getShiftAssignmentsByDate(LocalDate date) {
        return shiftAssignmentRepository.findByAssignmentDate(date);
    }

    public List<ShiftAssignment> getShiftAssignmentsByDateAndShift(LocalDate date, String shiftId) {
        return shiftAssignmentRepository.findByAssignmentDateAndShiftId(date, shiftId);
    }

    public List<ShiftAssignment> getShiftAssignmentsByEmployee(String networkId) {
        return shiftAssignmentRepository.findByNetworkId(networkId);
    }

    public ShiftAssignment createShiftAssignment(ShiftAssignmentRequest request) {
        // Validate employee exists
        Optional<Employee> employee = employeeRepository.findById(request.getNetworkId());
        if (!employee.isPresent()) {
            throw new RuntimeException("Employee not found with ID: " + request.getNetworkId());
        }

        // Validate shift type exists
        Optional<ShiftType> shiftType = shiftTypeRepository.findById(request.getShiftId());
        if (!shiftType.isPresent()) {
            throw new RuntimeException("Shift type not found with ID: " + request.getShiftId());
        }

        // Check if assignment already exists for this employee on this date
        List<ShiftAssignment> existingAssignments = shiftAssignmentRepository
                .findByNetworkIdAndDate(request.getNetworkId(), request.getAssignmentDate());
        if (!existingAssignments.isEmpty()) {
            throw new RuntimeException("Employee already has a shift assignment for this date");
        }

        ShiftAssignment assignment = new ShiftAssignment();
        assignment.setNetworkId(request.getNetworkId());
        assignment.setShiftId(request.getShiftId());
        assignment.setAssignmentDate(request.getAssignmentDate());
        assignment.setCreatedDateTime(LocalDateTime.now());

        return shiftAssignmentRepository.save(assignment);
    }

    public void deleteShiftAssignment(Long assignmentId) {
        shiftAssignmentRepository.deleteById(assignmentId);
    }
}

