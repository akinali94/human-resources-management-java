package com.humanresources.repository.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class UnitOfWorkImpl implements UnitOfWork {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void save() {
        entityManager.flush();
    }
    
    @Override
    @Transactional
    public void saveAsync() {
        // Spring Data JPA don't support Async directly. I need to implement differently
        save();
    }
}