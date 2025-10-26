package com.humanresources.repository.base;

public interface UnitOfWork {
    void save();
    void saveAsync();
}