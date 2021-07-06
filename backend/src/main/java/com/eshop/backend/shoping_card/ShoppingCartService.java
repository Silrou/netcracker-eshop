package com.eshop.backend.shoping_card;

import com.eshop.backend.product.dao.models.ProductModel;

import java.util.List;

public interface ShoppingCartService {
    void checkProductInStock(List<ProductModel> orderProducts);
    Long createOrder(List<ProductModel> orderProducts, Long id);
    void deleteProductFromOrder(ProductModel orderProduct, Long userId);
    List<ProductModel> getProductInCart(Long userId);
    Long addProductToShoppingCart(ProductModel productModel, Long userId);
}
