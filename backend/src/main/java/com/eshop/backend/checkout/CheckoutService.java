package com.eshop.backend.checkout;

import java.util.Date;
import java.util.List;

public interface CheckoutService {
    List<Integer> getDeliveryHours(Date deliveryDate);

    void updateOrderCart(OrderCheckoutDto orderCheckoutDto);
}
