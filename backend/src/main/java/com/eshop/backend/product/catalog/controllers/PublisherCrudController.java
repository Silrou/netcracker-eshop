package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.dao.Models.Genre;
import com.eshop.backend.dao.Models.Publisher;
import com.eshop.backend.product.catalog.service.Categories.Publisher.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherCrudController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherCrudController (PublisherService publisherService) {this.publisherService = publisherService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Publisher> getById(@PathVariable("id")Long id) {
        Publisher publisher = publisherService.getById(id);
        return new ResponseEntity(publisher, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Genre> getAllGenre() {
        List<Publisher> publishers = publisherService.getAll();
        return new ResponseEntity(publishers, HttpStatus.OK);
    }
}
