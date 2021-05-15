package com.eshop.backend.DAO.DataAccess.Product;

import com.eshop.backend.DAO.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Product product) {

    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }
}
