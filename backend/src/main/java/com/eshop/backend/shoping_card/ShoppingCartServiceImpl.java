package com.eshop.backend.shoping_card;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.auth.exceptions.OrderCartAmountException;
import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.product.dao.ProductDao;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final AuthorizedUserDao authorizedUserDao;
    private final ProductDao productDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, AuthorizedUserDao authorizedUserDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.authorizedUserDao = authorizedUserDao;
        this.productDao = productDao;
    }

    @Override
    public void checkProductInStock(List<ProductModel> orderProducts) throws OrderCartAmountException {
        List<Object> problemProducts = new ArrayList<>();
        for (ProductModel product : orderProducts) {
            if (product.getProductAmount() > shoppingCartDao.getProductsAmountById(product.getId())
                    || productDao.getStatusById(product.getId()).equals("INACTIVE")) {
                new ProductModel();
                problemProducts.add(ProductModel.builder()
                        .id(product.getId())
                        .productAmount(product.getProductAmount() - shoppingCartDao.getProductsAmountById(product.getId()))
                        .productStatus(productDao.getStatusById(product.getId()))
                        .build());
            }
        }

        if (problemProducts.size() > 0) {
            throw new OrderCartAmountException(problemProducts);
        }
    }

    private AuthorizedUserModel createAnonymousUser() {
        String login = UUID.randomUUID().toString();
        authorizedUserDao.create(AuthorizedUserModel.builder()
                .userLogin(login)
                .userPassword("password")
                .userRole(Role.USER.name())
                .userName("name")
                .userSurname("surname")
                .userRegistrationDate(new Date(System.currentTimeMillis()))
                .userStatus(Role.INACTIVE.name())
                .userAddress("no address")
                .userNumber("no number")
                .build());
        return authorizedUserDao.getByLogin(login);
    }

    private OrderCartModel unReserveOrderCart(Long userId, OrderCartModel orderCart) {
        shoppingCartDao.updateStatusById(orderCart.getId(), "UNRESERVED");
        List<ProductModel> unreservedProducts = shoppingCartDao.getProductsByUserIdAndStatus(
                userId);
        for (ProductModel unreservedProduct : unreservedProducts) {
            unReserveProduct(unreservedProduct, orderCart.getId());
        }
        return shoppingCartDao.getLastOrderCartByUserId(userId);
    }

    private void unReserveProduct(ProductModel unreservedProducts, Long orderCartId) {
        int a = productDao.getAmountById(unreservedProducts.getId());
        int b = shoppingCartDao.getAmountById(unreservedProducts.getId(), orderCartId);
        int newAmount = productDao.getAmountById(unreservedProducts.getId()) + shoppingCartDao.getAmountById(unreservedProducts.getId(),orderCartId);
        productDao.updateAmountById(unreservedProducts.getId(), newAmount);
    }

    private OrderCartModel createOrderCart(AuthorizedUserModel user, String orderStatus) {
        shoppingCartDao.createOrderCart(OrderCartModel.builder()
                .userId(user.getId())
                .courierId((long) -1)
                .packageDescription("Description")
                .orderStatus(orderStatus)
                .totalPrice(0)
                .userName(user.getUserName())
                .deliveryTime(new Date(System.currentTimeMillis()))
                .fullAddress("no address")
                .dontDisturb(true)
                .build());
        return shoppingCartDao.getLastOrderCartByUserId(user.getId());
    }

    private void reserveOrderCart(List<ProductModel> orderProducts, OrderCartModel orderCart) {
        int totalPrice = 0;
        for (ProductModel product : orderProducts) {
            shoppingCartDao.createOrderProduct(OrderProductModel.builder()
                    .productId(product.getId())
                    .orderCardId(orderCart.getId())
                    .inCardProductAmount(product.getProductAmount())
                    .inCardProductPrice(product.getProductPrice())
                    .build());


            totalPrice += product.getProductPrice();
            int newAmount = productDao.getAmountById(product.getId()) - product.getProductAmount();
            productDao.updateAmountById(product.getId(), newAmount);
        }
        shoppingCartDao.updateOrderCartTotalPrice(orderCart.getId(), totalPrice);
    }


    @Override
    @Transactional
    public Long createOrder(List<ProductModel> orderProducts, Long id) {

        checkProductInStock(orderProducts);

        AuthorizedUserModel user = authorizedUserDao.getById(id);
        if (authorizedUserDao.getById(id) == null) {
            user = createAnonymousUser();
        }

        OrderCartModel orderCart = shoppingCartDao.getLastOrderCartByUserId(user.getId());

        if (orderCart != null) {
            if (orderCart.getOrderStatus().equals("RESERVED")) {
                unReserveOrderCart(user.getId(), orderCart);
            }
            shoppingCartDao.deleteOrderCartById(orderCart.getId());
        }


        orderCart = createOrderCart(user, "RESERVED");
        reserveOrderCart(orderProducts, orderCart);

        return user.getId();
    }

    @Override
    @Transactional
    public void deleteProductFromOrder(ProductModel orderProduct, Long userId) {
        AuthorizedUserModel user = authorizedUserDao.getById(userId);
        if (user != null) {
            OrderCartModel orderCart = shoppingCartDao.getLastOrderCartByUserId(user.getId());
            if (orderCart != null) {
                if (orderCart.getOrderStatus().equals("RESERVED")) {
                    unReserveProduct(orderProduct, orderCart.getId());
                }
                shoppingCartDao.deleteProductFromOrderCart(orderProduct.getId(), orderCart.getId());
                List<ProductModel> productInCart = shoppingCartDao.getProductsByUserIdAndStatus(user.getId());
                if (productInCart.size() == 0) {
                    shoppingCartDao.deleteOrderCartById(orderCart.getId());
                }
            }
        }
    }

    @Override
    public List<ProductModel> getProductInCart(Long userId) {
        return productDao.getProductInShoppingCart(userId);
    }

    @Override
    @Transactional
    public Long addProductToShoppingCart(ProductModel productModel, Long userId) {
        AuthorizedUserModel user = authorizedUserDao.getById(userId);
        if (authorizedUserDao.getById(userId) == null) {
            user = createAnonymousUser();
        }

        OrderCartModel orderCart = shoppingCartDao.getLastOrderCartByUserId(user.getId());

        if (orderCart == null) {
            orderCart = createOrderCart(user, "UNRESERVED");
        }
        if (orderCart.getOrderStatus().equals("RESERVED")) {
            orderCart = unReserveOrderCart(user.getId(), orderCart);
        }
        //add to cart new item
        shoppingCartDao.addProductToCart(productModel, orderCart.getId());

        return user.getId();
    }

}
