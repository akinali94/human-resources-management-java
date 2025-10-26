package com.humanresources.service;

import com.humanresources.model.entity.User;
import com.humanresources.model.enums.Role;
import com.humanresources.service.base.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService extends BaseService<User, Long>, UserDetailsService {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    List<User> findByRole(Role role);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    String generateRandomPassword();
    
    User registerUser(User user, Set<Role> roles);
    
    void updateUser(User user);
}