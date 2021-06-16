package com.eshop.backend.catalog.service;

import com.eshop.backend.product.dao.models.ProductModel;

import java.util.List;

<<<<<<< HEAD
public interface ProductService{
=======
public interface ProductService {
>>>>>>> develop
    List<ProductModel> getProductPage(int page, int size);
    List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<ProductModel> getAllOrderBy(int page, int size, String orderBy);
    List<ProductModel> getByName (String name);
    ProductModel getById(Long id);
    void create(ProductModel productModel);
    void update(ProductModel productModel);
}
