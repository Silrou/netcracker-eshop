package com.eshop.backend.auth.exceptions;

import java.util.List;

public class OrderCartAmountException extends WebException {

    public OrderCartAmountException(List<Object> problemProducts) {
        super("OrderCartAmountException", problemProducts);
    }
}
