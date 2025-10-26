package com.humanresources.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "expenditure_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "expenditure_name", nullable = false)
    private String expenditureName;
    
    @Column(name = "max_price")
    private BigDecimal maxPrice;
    
    @Column(name = "min_price")
    private BigDecimal minPrice = BigDecimal.ZERO;
}