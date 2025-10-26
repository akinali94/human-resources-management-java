package com.humanresources.model.entity;

import com.humanresources.model.entity.base.BaseEntity;
import com.humanresources.model.enums.GenderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_leave_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveType extends BaseEntity {
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "default_day", nullable = false)
    private int defaultDay;
    
    @Enumerated(EnumType.STRING)
    private GenderType gender;
}