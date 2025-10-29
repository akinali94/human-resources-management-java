package com.humanresources.datainit.initializers;

import com.humanresources.model.entity.Company;
import com.humanresources.model.entity.User;
import com.humanresources.model.enums.Gender;
import com.humanresources.model.enums.Role;
import com.humanresources.repository.CompanyRepository;
import com.humanresources.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserInitializer {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void initialize() {
        if (userRepository.count() == 0) {
            System.out.println("Initializing users...");
            
            Company company = companyRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("No company found. Initialize companies first."));
            
            // Admin user
            User admin = new User();
            admin.setUsername("admin.master@bugbusters.com");
            admin.setEmail("admin.master@bugbusters.com");
            admin.setPassword(passwordEncoder.encode("Passw0rd!"));
            admin.setFirstName("Kemal");
            admin.setLastName("Sancar");
            admin.setTitle("Admin");
            admin.setDepartment("System");
            admin.setPhoneNumber("05350000001");
            admin.setAddress("Ankara Cad. No:100");
            admin.setCompany(company);
            admin.setSalary(new BigDecimal("30000"));
            admin.setGender(Gender.MALE);
            admin.setBirthPlace("Ankara");
            admin.setIdNumber("12345678901");
            admin.setBirthDate(LocalDate.of(1980, 5, 12));
            admin.setHireDate(LocalDate.of(2020, 1, 15));
            admin.setProfileImage("https://picsum.photos/seed/admin/200");
            admin.setBackgroundImage("https://picsum.photos/1200/300");
            admin.setMaxAdvanceAmount(new BigDecimal("20000"));
            admin.setCurrentAdvanceAmount(BigDecimal.ZERO);
            admin.setEnabled(true);
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(Role.ROLE_ADMIN);
            admin.setRoles(adminRoles);
            
            userRepository.save(admin);
            
            // Manager 1
            User manager1 = new User();
            manager1.setUsername("manager.ayse@bugbusters.com");
            manager1.setEmail("manager.ayse@bugbusters.com");
            manager1.setPassword(passwordEncoder.encode("Passw0rd!"));
            manager1.setFirstName("Ayşe");
            manager1.setLastName("Yılmaz");
            manager1.setTitle("Manager");
            manager1.setDepartment("Development");
            manager1.setPhoneNumber("05350000002");
            manager1.setAddress("İzmir Sok. No:45");
            manager1.setCompany(company);
            manager1.setSalary(new BigDecimal("25000"));
            manager1.setGender(Gender.FEMALE);
            manager1.setBirthPlace("İzmir");
            manager1.setIdNumber("23456789012");
            manager1.setBirthDate(LocalDate.of(1985, 8, 20));
            manager1.setHireDate(LocalDate.of(2021, 3, 10));
            manager1.setProfileImage("https://picsum.photos/seed/manager1/200");
            manager1.setBackgroundImage("https://picsum.photos/1200/300");
            manager1.setMaxAdvanceAmount(new BigDecimal("15000"));
            manager1.setCurrentAdvanceAmount(BigDecimal.ZERO);
            manager1.setEnabled(true);
            Set<Role> managerRoles = new HashSet<>();
            managerRoles.add(Role.ROLE_MANAGER);
            manager1.setRoles(managerRoles);
            
            userRepository.save(manager1);
            
            // Employee 1
            User employee1 = new User();
            employee1.setUsername("employee.ali@bugbusters.com");
            employee1.setEmail("employee.ali@bugbusters.com");
            employee1.setPassword(passwordEncoder.encode("Passw0rd!"));
            employee1.setFirstName("Ali");
            employee1.setLastName("Demir");
            employee1.setTitle("Developer");
            employee1.setDepartment("Development");
            employee1.setPhoneNumber("05350000003");
            employee1.setAddress("Kadıköy Mah. No:78");
            employee1.setCompany(company);
            employee1.setSalary(new BigDecimal("18000"));
            employee1.setGender(Gender.MALE);
            employee1.setBirthPlace("Istanbul");
            employee1.setIdNumber("34567890123");
            employee1.setBirthDate(LocalDate.of(1990, 4, 15));
            employee1.setHireDate(LocalDate.of(2022, 2, 1));
            employee1.setProfileImage("https://picsum.photos/seed/employee1/200");
            employee1.setBackgroundImage("https://picsum.photos/1200/300");
            employee1.setMaxAdvanceAmount(new BigDecimal("12000"));
            employee1.setCurrentAdvanceAmount(BigDecimal.ZERO);
            employee1.setEnabled(true);
            Set<Role> employeeRoles = new HashSet<>();
            employeeRoles.add(Role.ROLE_EMPLOYEE);
            employee1.setRoles(employeeRoles);
            
            userRepository.save(employee1);
            
            // Employee 2
            User employee2 = new User();
            employee2.setUsername("employee.elif@bugbusters.com");
            employee2.setEmail("employee.elif@bugbusters.com");
            employee2.setPassword(passwordEncoder.encode("Passw0rd!"));
            employee2.setFirstName("Elif");
            employee2.setLastName("Kara");
            employee2.setTitle("QA Engineer");
            employee2.setDepartment("QA");
            employee2.setPhoneNumber("05350000005");
            employee2.setAddress("Ataşehir No:11");
            employee2.setCompany(company);
            employee2.setSalary(new BigDecimal("17500"));
            employee2.setGender(Gender.FEMALE);
            employee2.setBirthPlace("Adana");
            employee2.setIdNumber("56789012345");
            employee2.setBirthDate(LocalDate.of(1994, 9, 5));
            employee2.setHireDate(LocalDate.of(2023, 5, 20));
            employee2.setProfileImage("https://picsum.photos/seed/employee2/200");
            employee2.setBackgroundImage("https://picsum.photos/1200/300");
            employee2.setMaxAdvanceAmount(new BigDecimal("10000"));
            employee2.setCurrentAdvanceAmount(BigDecimal.ZERO);
            employee2.setEnabled(true);
            employee2.setRoles(employeeRoles);
            
            userRepository.save(employee2);
            
            System.out.println("Users initialization completed.");
        } else {
            System.out.println("Users already exist. Skipping initialization.");
        }
    }
}