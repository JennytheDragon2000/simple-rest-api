package org.example.simplerestapi.repository;

import org.example.simplerestapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByActive(Boolean active);

    @Query("SELECT DISTINCT e FROM Employee e " +
            "JOIN e.shiftAssignments sa " +
            "WHERE sa.assignmentDate = :date")
    List<Employee> findEmployeesByDate(@Param("date") LocalDate date);

    @Query("SELECT DISTINCT e FROM Employee e " +
            "JOIN e.shiftAssignments sa " +
            "WHERE sa.assignmentDate = :date AND sa.shiftId = :shiftId")
    List<Employee> findEmployeesByDateAndShift(@Param("date") LocalDate date, @Param("shiftId") String shiftId);
}