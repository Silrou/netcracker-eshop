package com.eshop.backend.catalog.service;

import com.eshop.backend.product.dao.models.FilterModel;
import com.eshop.backend.product.dao.models.ProductModel;

import java.util.List;

public interface ProductService {

    List<ProductModel> getProductPage(int page, int size);
    List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<ProductModel> getAllOrderBy(int page, int size, String orderBy);
    List<ProductModel> getByName (String name);
    List<ProductModel> getFiltered(int page, int size, FilterModel filterModel);
    List<ProductModel> getSearchedOrderedFiltered(int page, int size, String search, String orderBy, FilterModel filterModel);
    ProductModel getById(Long id);
    Integer getNumberOfSearchedOrderedFiltered (String search, String orderBy, FilterModel filterModel);
    void create(ProductModel productModel);
    void update(ProductModel productModel);
    Long getProductCount();
}
