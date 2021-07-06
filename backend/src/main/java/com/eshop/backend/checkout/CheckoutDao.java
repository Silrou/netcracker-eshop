package com.eshop.backend.checkout;

import com.eshop.backend.shoping_card.OrderCartModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import com.eshop.backend.utils.CrudDao;

import java.util.Date;
import java.util.List;

public interface CheckoutDao extends CrudDao<OrderCartModel> {
    List<Integer> getHoursByDate(Date deliveryDate);

    void updateOrderCart(OrderCheckoutDto orderCheckoutDto, Long orderCartId);
}
