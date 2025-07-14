package org.example.simplerestapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ShiftAssignmentRequest {
    @NotBlank(message = "Network ID is required")
    private String networkId;

    @NotBlank(message = "Shift ID is required")
    private String shiftId;

    @NotNull(message = "Assignment date is required")
    private LocalDate assignmentDate;

    // Constructors
    public ShiftAssignmentRequest() {}

    public ShiftAssignmentRequest(String networkId, String shiftId, LocalDate assignmentDate) {
        this.networkId = networkId;
        this.shiftId = shiftId;
        this.assignmentDate = assignmentDate;
    }

    // Getters and Setters
    public String getNetworkId() { return networkId; }
    public void setNetworkId(String networkId) { this.networkId = networkId; }

    public String getShiftId() { return shiftId; }
    public void setShiftId(String shiftId) { this.shiftId = shiftId; }

    public LocalDate getAssignmentDate() { return assignmentDate; }
    public void setAssignmentDate(LocalDate assignmentDate) { this.assignmentDate = assignmentDate; }
}
