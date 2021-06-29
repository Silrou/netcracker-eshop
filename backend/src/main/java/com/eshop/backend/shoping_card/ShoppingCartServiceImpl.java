package com.eshop.backend.shoping_card;

import com.eshop.backend.auth.exceptions.OrderCartAmountException;
import com.eshop.backend.product.dao.models.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public void checkProductInStock(List<ProductModel> orderProducts) throws OrderCartAmountException{
        List<Long> productIds = orderProducts.stream()
                .map(ProductModel::getId).collect(Collectors.toList());

        boolean amountProblem = false;
        List<Object> problemProducts = new ArrayList<>();
        for (ProductModel prod: orderProducts) {
            if (prod.getProductAmount() > shoppingCartDao.getProductsAmountById(prod.getId())) {
//                throw new OrderCartAmountException();
                new ProductModel();
                problemProducts.add(ProductModel.builder()
                        .id(prod.getId())
                        .productAmount(prod.getProductAmount() - shoppingCartDao.getProductsAmountById(prod.getId()))
                        .build());
                amountProblem = true;
            }
        }

        if (amountProblem) {
            throw new OrderCartAmountException(problemProducts);
        }


        List<ProductModel> storageProduct = shoppingCartDao.getProductsByIds(productIds);
    }
}
