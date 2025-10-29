package com.humanresources.web.dto;

import com.humanresources.model.enums.CompanyTitle;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    
    private Long id;
    
    @NotBlank(message = "Company name is required")
    private String companyName;
    
    @NotNull(message = "Company title is required")
    private CompanyTitle companyTitle;
    
    @NotBlank(message = "Mersis number is required")
    private String mersisNo;
    
    @NotBlank(message = "Tax number is required")
    private String taxNumber;
    
    private String logo;
    
    @NotBlank(message = "Phone number is required")
    private String telephoneNumber;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;
    
    @NotBlank(message = "Employee count is required")
    private String employeeNumber;
    
    @NotNull(message = "Foundation date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate foundationYear;
    
    @NotNull(message = "Contract start date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractStartDate;
    
    @NotNull(message = "Contract end date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    
    private boolean isActive = true;
    
    private transient MultipartFile logoFile;
}