package com.eshop.backend.product.catalog.controllers;


import com.eshop.backend.dao.Models.Author;
import com.eshop.backend.dao.Models.Genre;
import com.eshop.backend.product.catalog.service.Categories.Author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorCrudController {

    private final AuthorService authorService;

    @Autowired
    public AuthorCrudController(AuthorService authorService) {this.authorService = authorService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id")Long id) {
        Author author = authorService.getById(id);
        return new ResponseEntity(author, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Genre> getAllGenre() {
        List<Author> authors = authorService.getAll();
        return new ResponseEntity(authors, HttpStatus.OK);
    }
}
