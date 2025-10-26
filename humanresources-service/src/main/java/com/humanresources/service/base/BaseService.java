package com.humanresources.service.base;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    
    T save(T entity);
    
    List<T> findAll();
    
    Optional<T> findById(ID id);
    
    void delete(T entity);
    
    void deleteById(ID id);
    
    boolean existsById(ID id);
    
    long count();
}