package com.eshop.backend.courier;

import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.courier.service.CourierService;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CourierController {
    @Autowired
    CourierService courierService;

    @GetMapping("/courier/cabinet/get")
    public ResponseEntity<List<CourierModel>> getMyTimeTable() {
        List<CourierModel> courierModel = courierService.getMyTimeTable();
        return new ResponseEntity<>(courierModel, HttpStatus.OK);
    }
    @GetMapping("/courier/cabinet/setordercart")
    public ResponseEntity<List<CourierModel>> setOrderCart() {
        courierService.setOrderCart();
        List<CourierModel> courierModel = courierService.getMyTimeTable();
        return new ResponseEntity<>(courierModel, HttpStatus.OK);
    }
}
