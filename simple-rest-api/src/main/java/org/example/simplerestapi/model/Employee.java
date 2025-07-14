package org.example.simplerestapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "employee_dim")
public class Employee {
    @Id
    @Column(name = "network_id")
    private String networkId;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "position")
    private String position;

    @Column(name = "active")
    private Boolean active = true;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("employee-statuses")
    private List<EmployeeStatus> employeeStatuses;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("employee-assignments")
    private List<ShiftAssignment> shiftAssignments;

    // Constructors
    public Employee() {}

    public Employee(String networkId, String firstName, String lastName, String email,
                    String country, String position, Boolean active) {
        this.networkId = networkId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.position = position;
        this.active = active;
    }

    // Getters and Setters
    public String getNetworkId() { return networkId; }
    public void setNetworkId(String networkId) { this.networkId = networkId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public List<EmployeeStatus> getEmployeeStatuses() { return employeeStatuses; }
    public void setEmployeeStatuses(List<EmployeeStatus> employeeStatuses) { this.employeeStatuses = employeeStatuses; }

    public List<ShiftAssignment> getShiftAssignments() { return shiftAssignments; }
    public void setShiftAssignments(List<ShiftAssignment> shiftAssignments) { this.shiftAssignments = shiftAssignments; }
}