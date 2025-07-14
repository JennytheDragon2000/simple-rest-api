package org.example.simplerestapi.service;

import org.example.simplerestapi.model.ShiftType;
import org.example.simplerestapi.repository.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftTypeService {

    @Autowired
    private ShiftTypeRepository shiftTypeRepository;

    public List<ShiftType> getAllShiftTypes() {
        return shiftTypeRepository.findAll();
    }

    public Optional<ShiftType> getShiftTypeById(String shiftId) {
        return shiftTypeRepository.findById(shiftId);
    }

    public ShiftType getShiftTypeByCode(String shiftCode) {
        return shiftTypeRepository.findByShiftCode(shiftCode);
    }

    public ShiftType saveShiftType(ShiftType shiftType) {
        return shiftTypeRepository.save(shiftType);
    }

    public ShiftType updateShiftType(String shiftId, ShiftType shiftTypeDetails) {
        ShiftType shiftType = shiftTypeRepository.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift type not found"));

        shiftType.setShiftCode(shiftTypeDetails.getShiftCode());
        shiftType.setShiftName(shiftTypeDetails.getShiftName());
        shiftType.setShiftStartTime(shiftTypeDetails.getShiftStartTime());
        shiftType.setShiftEndTime(shiftTypeDetails.getShiftEndTime());
        shiftType.setShiftTimeZone(shiftTypeDetails.getShiftTimeZone());

        return shiftTypeRepository.save(shiftType);
    }

    public void deleteShiftType(String shiftId) {
        shiftTypeRepository.deleteById(shiftId);
    }
}