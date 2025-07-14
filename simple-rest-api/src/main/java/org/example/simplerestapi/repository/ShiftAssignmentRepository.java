package org.example.simplerestapi.repository;

import org.example.simplerestapi.model.ShiftAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShiftAssignmentRepository extends JpaRepository<ShiftAssignment, Long> {

    List<ShiftAssignment> findByNetworkId(String networkId);

    List<ShiftAssignment> findByAssignmentDate(LocalDate assignmentDate);

    List<ShiftAssignment> findByAssignmentDateAndShiftId(LocalDate assignmentDate, String shiftId);

    @Query("SELECT sa FROM ShiftAssignment sa WHERE sa.networkId = :networkId AND sa.assignmentDate = :date")
    List<ShiftAssignment> findByNetworkIdAndDate(@Param("networkId") String networkId, @Param("date") LocalDate date);
}