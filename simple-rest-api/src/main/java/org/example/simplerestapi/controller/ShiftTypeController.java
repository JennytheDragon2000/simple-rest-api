package org.example.simplerestapi.controller;

import org.example.simplerestapi.model.ShiftType;
import org.example.simplerestapi.service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shift-types")
@CrossOrigin(origins = "*")
public class ShiftTypeController {

    @Autowired
    private ShiftTypeService shiftTypeService;

    // GET /api/shift-types - Get all shift types
    @GetMapping
    public ResponseEntity<List<ShiftType>> getAllShiftTypes() {
        List<ShiftType> shiftTypes = shiftTypeService.getAllShiftTypes();
        return ResponseEntity.ok(shiftTypes);
    }

    // GET /api/shift-types/{shiftId} - Get shift type by ID
    @GetMapping("/{shiftId}")
    public ResponseEntity<ShiftType> getShiftTypeById(@PathVariable String shiftId) {
        Optional<ShiftType> shiftType = shiftTypeService.getShiftTypeById(shiftId);
        return shiftType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/shift-types - Create new shift type
    @PostMapping
    public ResponseEntity<ShiftType> createShiftType(@Valid @RequestBody ShiftType shiftType) {
        try {
            ShiftType savedShiftType = shiftTypeService.saveShiftType(shiftType);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedShiftType);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/shift-types/{shiftId} - Update shift type
    @PutMapping("/{shiftId}")
    public ResponseEntity<ShiftType> updateShiftType(
            @PathVariable String shiftId,
            @Valid @RequestBody ShiftType shiftType) {
        try {
            ShiftType updatedShiftType = shiftTypeService.updateShiftType(shiftId, shiftType);
            return ResponseEntity.ok(updatedShiftType);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/shift-types/{shiftId} - Delete shift type
    @DeleteMapping("/{shiftId}")
    public ResponseEntity<Void> deleteShiftType(@PathVariable String shiftId) {
        try {
            shiftTypeService.deleteShiftType(shiftId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}