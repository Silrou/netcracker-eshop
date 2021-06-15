package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.dao.Models.Genre;
import com.eshop.backend.product.catalog.service.Categories.Genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreCrudController {

    private final GenreService genreService;

    @Autowired
    public GenreCrudController(GenreService genreService) {this.genreService = genreService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Genre> getById(@PathVariable("id")Long id) {
        Genre genre = genreService.getById(id);
        return new ResponseEntity(genre, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Genre> getAllGenre() {
        List <Genre> genres = genreService.getAll();
        return new ResponseEntity(genres, HttpStatus.OK);
    }
}
