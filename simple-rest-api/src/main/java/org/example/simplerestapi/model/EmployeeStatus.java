package org.example.simplerestapi.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_status_fact")
public class EmployeeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "network_id")
    private String networkId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "status_type")
    private String statusType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_id", insertable = false, updatable = false)
    @JsonBackReference("employee-statuses")
    private Employee employee;

    // Constructors
    public EmployeeStatus() {}

    public EmployeeStatus(String networkId, LocalDate date, LocalDateTime timestamp, String statusType) {
        this.networkId = networkId;
        this.date = date;
        this.timestamp = timestamp;
        this.statusType = statusType;
    }

    // Getters and Setters
    public Long getStatusId() { return statusId; }
    public void setStatusId(Long statusId) { this.statusId = statusId; }

    public String getNetworkId() { return networkId; }
    public void setNetworkId(String networkId) { this.networkId = networkId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getStatusType() { return statusType; }
    public void setStatusType(String statusType) { this.statusType = statusType; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}