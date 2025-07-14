package org.example.simplerestapi.controller;

import org.example.simplerestapi.dto.ShiftAssignmentRequest;
import org.example.simplerestapi.model.ShiftAssignment;
import org.example.simplerestapi.service.ShiftAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shift-assignments")
@CrossOrigin(origins = "*")
public class ShiftAssignmentController {


    @Autowired
    private ShiftAssignmentService shiftAssignmentService;

    // GET /api/shift-assignments - Get all shift assignments
    @GetMapping
    public ResponseEntity<List<ShiftAssignment>> getAllShiftAssignments() {
        List<ShiftAssignment> assignments = shiftAssignmentService.getAllShiftAssignments();
        return ResponseEntity.ok(assignments);
    }

    // GET /api/shift-assignments/by-date?date=YYYY-MM-DD - Get shift assignments by date
    @GetMapping("/by-date")
    public ResponseEntity<List<ShiftAssignment>> getShiftAssignmentsByDate(@RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<ShiftAssignment> assignments = shiftAssignmentService.getShiftAssignmentsByDate(localDate);
            return ResponseEntity.ok(assignments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/shift-assignments/by-date-and-shift?date=YYYY-MM-DD&shiftId=SHIFT_ID - Get shift assignments by date and shift
    @GetMapping("/by-date-and-shift")
    public ResponseEntity<List<ShiftAssignment>> getShiftAssignmentsByDateAndShift(
            @RequestParam String date,
            @RequestParam String shiftId) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<ShiftAssignment> assignments = shiftAssignmentService.getShiftAssignmentsByDateAndShift(localDate, shiftId);
            return ResponseEntity.ok(assignments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/shift-assignments/by-employee/{networkId} - Get shift assignments by employee
    @GetMapping("/by-employee/{networkId}")
    public ResponseEntity<List<ShiftAssignment>> getShiftAssignmentsByEmployee(@PathVariable String networkId) {
        List<ShiftAssignment> assignments = shiftAssignmentService.getShiftAssignmentsByEmployee(networkId);
        return ResponseEntity.ok(assignments);
    }

    // POST /api/shift-assignments - Create new shift assignment
    @PostMapping
    public ResponseEntity<ShiftAssignment> createShiftAssignment(@Valid @RequestBody ShiftAssignmentRequest request) {
        try {
            ShiftAssignment assignment = shiftAssignmentService.createShiftAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(assignment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/shift-assignments/{assignmentId} - Delete shift assignment
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteShiftAssignment(@PathVariable Long assignmentId) {
        try {
            shiftAssignmentService.deleteShiftAssignment(assignmentId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}