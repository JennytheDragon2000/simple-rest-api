package org.example.simplerestapi.repository;

import org.example.simplerestapi.model.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftTypeRepository extends JpaRepository<ShiftType, String> {
    ShiftType findByShiftCode(String shiftCode);
}
