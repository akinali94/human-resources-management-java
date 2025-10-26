package com.humanresources.service.base;

import com.humanresources.repository.base.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    
    protected final BaseRepository<T, ID> repository;
    
    public BaseServiceImpl(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }
    
    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }
}