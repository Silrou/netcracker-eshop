package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.utils.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductModel> {
    List<ProductModel> getProductPage(int page, int size);
    List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter);
    List<ProductModel> getAllOrderBy(int page, int size, String orderBy);
    List<ProductModel> getByName(String name);
}
