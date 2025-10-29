package com.humanresources.datainit.initializers;

import com.humanresources.model.entity.Company;
import com.humanresources.model.enums.CompanyTitle;
import com.humanresources.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CompanyInitializer {

    private final CompanyRepository companyRepository;
    
    public void initialize() {
        if (companyRepository.count() == 0) {
            System.out.println("Initializing companies...");
            
            Company company = new Company();
            company.setCompanyName("BugBusters");
            company.setCompanyTitle(CompanyTitle.LIMITED);
            company.setMersisNo("1234567890123456");
            company.setTaxNumber("1234567890");
            company.setLogo("https://picsum.photos/seed/bugbusters/120");
            company.setTelephoneNumber("02120000000");
            company.setAddress("Istanbul");
            company.setEmail("info@bugbusters.test");
            company.setEmployeeNumber("100");
            company.setFoundationYear(LocalDate.of(2015, 1, 1));
            company.setContractStartDate(LocalDate.now().minusYears(1));
            company.setContractEndDate(LocalDate.now().plusYears(1));
            company.setActive(true);
            
            companyRepository.save(company);
            
            System.out.println("Company initialization completed.");
        } else {
            System.out.println("Companies already exist. Skipping initialization.");
        }
    }
}