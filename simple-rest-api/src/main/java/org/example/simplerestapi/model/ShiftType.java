package org.example.simplerestapi.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "shift_type_dim")
public class ShiftType {
    @Id
    @Column(name = "shift_id")
    private String shiftId;

    @Column(name = "shift_code")
    private String shiftCode;

    @Column(name = "shift_name")
    private String shiftName;

    @Column(name = "shift_start_time")
    private LocalTime shiftStartTime;

    @Column(name = "shift_end_time")
    private LocalTime shiftEndTime;

    @Column(name = "shift_time_zone")
    private String shiftTimeZone;

    @OneToMany(mappedBy = "shiftType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("shift-assignments")
    private List<ShiftAssignment> shiftAssignments;

    // Constructors
    public ShiftType() {}

    public ShiftType(String shiftId, String shiftCode, String shiftName,
                     LocalTime shiftStartTime, LocalTime shiftEndTime, String shiftTimeZone) {
        this.shiftId = shiftId;
        this.shiftCode = shiftCode;
        this.shiftName = shiftName;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftTimeZone = shiftTimeZone;
    }

    // Getters and Setters
    public String getShiftId() { return shiftId; }
    public void setShiftId(String shiftId) { this.shiftId = shiftId; }

    public String getShiftCode() { return shiftCode; }
    public void setShiftCode(String shiftCode) { this.shiftCode = shiftCode; }

    public String getShiftName() { return shiftName; }
    public void setShiftName(String shiftName) { this.shiftName = shiftName; }

    public LocalTime getShiftStartTime() { return shiftStartTime; }
    public void setShiftStartTime(LocalTime shiftStartTime) { this.shiftStartTime = shiftStartTime; }

    public LocalTime getShiftEndTime() { return shiftEndTime; }
    public void setShiftEndTime(LocalTime shiftEndTime) { this.shiftEndTime = shiftEndTime; }

    public String getShiftTimeZone() { return shiftTimeZone; }
    public void setShiftTimeZone(String shiftTimeZone) { this.shiftTimeZone = shiftTimeZone; }

    public List<ShiftAssignment> getShiftAssignments() { return shiftAssignments; }
    public void setShiftAssignments(List<ShiftAssignment> shiftAssignments) { this.shiftAssignments = shiftAssignments; }
}
