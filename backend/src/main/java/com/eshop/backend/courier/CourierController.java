package com.eshop.backend.courier;

import com.eshop.backend.courier.model.CourierModel;
import com.eshop.backend.courier.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class CourierController {
    @Autowired
    CourierService courierService;

    @GetMapping("/courier/cabinet/get/{courierid}")
    public ResponseEntity<List<CourierModel>> getMyTimeTable(@PathVariable("courierid") long courierid) {
        List<CourierModel> courierModel = courierService.getMyTimeTable(courierid);
        return new ResponseEntity<>(courierModel, HttpStatus.OK);
    }
    @PutMapping("/courier/cabinet/{setordercart}")
    public ResponseEntity<Object> setOrderCartStatus(@PathVariable("setordercart") long setordercart) {
        courierService.setOrderCartStatus(setordercart);
        List<CourierModel> courierModel = courierService.getMyTimeTable(courierid);
        return new ResponseEntity<>(courierModel, HttpStatus.OK);
    }
}
