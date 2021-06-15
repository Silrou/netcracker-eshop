package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.dao.Models.CoverType;
import com.eshop.backend.dao.Models.Genre;
import com.eshop.backend.product.catalog.service.Categories.CoverType.CoverTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cover-type")
public class CoverTypeCrudController {

    private final CoverTypeService coverTypeService;

    @Autowired
    public CoverTypeCrudController (CoverTypeService coverTypeService) {this.coverTypeService = coverTypeService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CoverType> getById(@PathVariable("id")Long id) {
        CoverType coverType = coverTypeService.getById(id);
        return new ResponseEntity(coverType, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<CoverType> getAllGenre() {
        List<CoverType> coverTypes = coverTypeService.getAll();
        return new ResponseEntity(coverTypes, HttpStatus.OK);
    }
}
