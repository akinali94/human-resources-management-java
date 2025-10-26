package com.humanresources.repository;

import com.humanresources.model.entity.User;
import com.humanresources.model.enums.Role;
import com.humanresources.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    
    // do not need to implement codes for these methods because of Spring Data JPA
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRolesContaining(Role role);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    List<User> findByCompanyId(Long companyId);
}