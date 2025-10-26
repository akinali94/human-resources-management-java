package com.humanresources.model.entity;

import com.humanresources.model.entity.base.BaseEntity;
import com.humanresources.model.enums.GenderType;
import com.humanresources.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "background_image_url")
    private String backgroundImageUrl;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "second_name")
    private String secondName;
    
    @Column(nullable = false)
    private String surname;
    
    @Column(name = "second_surname")
    private String secondSurname;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "is_enabled")
    private boolean isEnabled = true;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();
    
    @Column(name = "advance_amount")
    private BigDecimal advanceAmount = BigDecimal.ZERO;
    
    @Column(name = "birth_place")
    private String birthPlace;
    
    private String tc;
    
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @Column(name = "hired_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredDate;
    
    @Column(name = "resignation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate resignationDate;
    
    private String title;
    
    private String section;
    
    @Column(name = "telephone_number")
    private String telephoneNumber;
    
    private String address;
    
    @Column(name = "company_name")
    private String companyName;
    
    private BigDecimal salary = BigDecimal.ZERO;
    
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    
    @Column(name = "max_advance_amount")
    private BigDecimal maxAdvanceAmount = BigDecimal.ZERO;
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    
    @Transient
    public String getFullName() {
        if (secondName == null && secondSurname == null) {
            return name + " " + surname;
        } else if (secondName == null) {
            return name + " " + surname + " " + secondSurname;
        } else if (secondSurname == null) {
            return name + " " + secondName + " " + surname;
        } else {
            return name + " " + secondName + " " + surname + " " + secondSurname;
        }
    }
    
    @Transient
    public boolean isActive() {
        return hiredDate != null && resignationDate == null;
    }
    
    // UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}