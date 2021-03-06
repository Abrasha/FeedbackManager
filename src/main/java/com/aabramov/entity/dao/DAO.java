package com.aabramov.entity.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andrii Abramov on 12/16/15.
 */
public interface DAO<T extends Serializable> {
    List<T> getAll();
    
    T get(int id);
    
    void save(T obj);
    
    void update(T obj);
    
    void delete(T obj);
    
    void delete(int id);
    
}
