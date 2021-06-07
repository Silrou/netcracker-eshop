package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.DAO.DataAccess.Product.ProductDao;
import com.eshop.backend.DAO.DataAccess.Product.ProductDaoImpl;
import com.eshop.backend.DAO.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manager/workspace/product")
public class ProductCatalogController {

    private final ProductDao productDao;

    @Autowired
    public ProductCatalogController(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productDao.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productDao.create(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        productDao.update(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/remove/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productDao.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
