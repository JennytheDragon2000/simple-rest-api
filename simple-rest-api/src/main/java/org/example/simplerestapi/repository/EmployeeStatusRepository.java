package org.example.simplerestapi.repository;

import org.example.simplerestapi.model.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Long> {
    List<EmployeeStatus> findByNetworkId(String networkId);
    List<EmployeeStatus> findByDate(LocalDate date);
    List<EmployeeStatus> findByNetworkIdAndDate(String networkId, LocalDate date);
}