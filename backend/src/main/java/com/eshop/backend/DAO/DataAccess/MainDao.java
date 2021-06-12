package com.eshop.backend.DAO.DataAccess;


import java.util.List;

public interface MainDao <T>{
    void create(T model);

    T getById(Long id);

    List<T> getAll();

    void update(T model);

    void delete(Long id);
}
