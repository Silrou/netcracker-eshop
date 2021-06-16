package com.eshop.backend.utils;


import java.util.List;

public interface CrudDao<T>{
    void create(T model);

    T getById(int id);

    List<T> getAll();

    void update(T model);

    void delete(Long id);
}
