package com.humanresources.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    // JpaRepository already included these methods:
    // save(T entity), findById(ID id), findAll(), delete(T entity), etc.
    
    // JpaSpecificationExecutor can make filtration on criteria.
    // findAll(Specification<T> spec)
}