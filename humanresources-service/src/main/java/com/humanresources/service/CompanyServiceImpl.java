package com.humanresources.service;

import com.humanresources.model.entity.Company;
import com.humanresources.model.entity.User;
import com.humanresources.model.enums.CompanyTitle;
import com.humanresources.repository.CompanyRepository;
import com.humanresources.repository.UserRepository;
import com.humanresources.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, Long> implements CompanyService {
    
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    
    public CompanyServiceImpl(CompanyRepository companyRepository, UserRepository userRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Company> findByCompanyTitle(CompanyTitle companyTitle) {
        return companyRepository.findByCompanyTitle(companyTitle);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Company> findActiveCompanies() {
        return companyRepository.findByIsActiveTrue();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findEmployeesByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }
}