package com.humanresources.model.entity;

import com.humanresources.model.entity.base.BaseEntity;
import com.humanresources.model.enums.CompanyTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {
    
    @Column(name = "company_name", nullable = false)
    private String companyName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "company_title")
    private CompanyTitle companyTitle;
    
    @Column(name = "mersis_no")
    private String mersisNo;
    
    @Column(name = "tax_number")
    private String taxNumber;
    
    private String logo;
    
    @Column(name = "telephone_number")
    private String telephoneNumber;
    
    private String address;
    
    private String email;
    
    @Column(name = "employee_number")
    private String employeeNumber;
    
    @Column(name = "foundation_year")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate foundationYear;
    
    @Column(name = "contract_start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    
    @Column(name = "contract_end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<User> employees = new HashSet<>();
}