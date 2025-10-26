package com.humanresources.repository;

import com.humanresources.model.entity.Company;
import com.humanresources.model.enums.CompanyTitle;
import com.humanresources.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends BaseRepository<Company, Long> {
    
    Optional<Company> findByCompanyName(String companyName);
    
    List<Company> findByIsActiveTrue();
    
    List<Company> findByCompanyTitle(CompanyTitle companyTitle);
}