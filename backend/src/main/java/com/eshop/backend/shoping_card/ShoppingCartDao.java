package com.eshop.backend.shoping_card;

import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.utils.CrudDao;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface ShoppingCartDao extends CrudDao<ProductModel> {
    List<ProductModel> getProductsByIds(List<Long> ids);
    List<ProductModel> getProductsByUserIdAndStatus(Long id);
    int getProductsAmountById(Long id);
    void createOrderCart(OrderCartModel orderCartModel);
    void createOrderProduct(OrderProductModel orderProductModel);
    OrderCartModel getLastOrderCartByUserId(Long id);
    void updateOrderCartTotalPrice(Long id, Integer totalPrice);
    void deleteOrderCartById(Long id);
    void deleteProductFromOrderCart(Long productId, Long orderCartId);
    void addProductToCart(ProductModel productModel, Long orderCaryId);
    void updateStatusById(Long id, String status);
}
