package com.humanresources.model.entity;

import com.humanresources.model.entity.base.BaseEntity;
import com.humanresources.model.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "individual_advances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualAdvance extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    private Currency currency;
    
    private String explanation;
    
    @Column(name = "request_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate = LocalDate.now();
    
    @Column(name = "approval_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate approvalDate;
    
    @Column(name = "approval_status")
    private Boolean approvalStatus;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private BigDecimal remain;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;
}