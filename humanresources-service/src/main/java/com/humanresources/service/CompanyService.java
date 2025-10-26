package com.humanresources.service;

import com.humanresources.model.entity.Company;
import com.humanresources.model.entity.User;
import com.humanresources.model.enums.CompanyTitle;
import com.humanresources.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface CompanyService extends BaseService<Company, Long> {
    
    Optional<Company> findByCompanyName(String companyName);
    
    List<Company> findByCompanyTitle(CompanyTitle companyTitle);
    
    List<Company> findActiveCompanies();
    
    List<User> findEmployeesByCompanyId(Long companyId);
}