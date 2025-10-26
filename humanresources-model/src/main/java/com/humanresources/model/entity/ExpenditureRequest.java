package com.humanresources.model.entity;

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
@Table(name = "expenditure_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Enumerated(EnumType.STRING)
    private Currency currency;
    
    @Column(name = "approval_status")
    private Boolean approvalStatus;
    
    @Column(name = "request_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requestDate = LocalDate.now();
    
    @Column(name = "approval_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate approvalDate;
    
    @Column(name = "amount_of_expenditure", nullable = false)
    private BigDecimal amountOfExpenditure;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name = "expenditure_type_id", nullable = false)
    private ExpenditureType expenditureType;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;
}