package com.eshop.backend.user.order_history.controllers;

import com.eshop.backend.order_card.dao.models.OrderCardModel;
import com.eshop.backend.user.order_history.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-history")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderCardModel>> getAllOrders(@PathVariable("id")Long id) {
        List<OrderCardModel> orderHistory = orderHistoryService.getAllByUserId(id);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

}
