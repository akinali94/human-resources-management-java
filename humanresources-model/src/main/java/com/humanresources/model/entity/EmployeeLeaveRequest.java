package com.humanresources.model.entity;

import com.humanresources.model.entity.base.BaseEntity;
import com.humanresources.model.enums.GenderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "employee_leave_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveRequest extends BaseEntity {
    
    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    @Column(name = "date_request", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRequest = LocalDate.now();
    
    private Boolean approved;
    
    @Column(name = "leave_approval_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate leaveApprovalDate;
    
    @Column(name = "leave_type_name")
    private String leaveTypeName;
    
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    
    @ManyToOne
    @JoinColumn(name = "requesting_id", nullable = false)
    private User requesting;
    
    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    private EmployeeLeaveType leaveType;
    
    @Transient
    public int getNumberOfDaysOff() {
        return (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);
    }
}