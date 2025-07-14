package org.example.simplerestapi.repository;

import org.example.simplerestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN Employee e ON u.email = e.email " +
            "JOIN e.shiftAssignments sa " +
            "WHERE sa.assignmentDate = :date")
    List<User> findUsersByDate(@Param("date") LocalDate date);
    
    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN Employee e ON u.email = e.email " +
            "JOIN e.shiftAssignments sa " +
            "WHERE sa.assignmentDate = :date AND sa.shiftId = :shiftId")
    List<User> findUsersByDateAndShift(@Param("date") LocalDate date, @Param("shiftId") String shiftId);
}