package com.eshop.backend.product.catalog.controllers;

import com.eshop.backend.dao.Models.Language;
import com.eshop.backend.product.catalog.service.Categories.Language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageCrudController {

    private final LanguageService languageService;

    @Autowired
    public LanguageCrudController (LanguageService languageService) {this.languageService = languageService;}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Language> getById(@PathVariable("id")Long id) {
        Language language = languageService.getById(id);
        return new ResponseEntity(language, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Language> getAllGenre() {
        List<Language> languages = languageService.getAll();
        return new ResponseEntity(languages, HttpStatus.OK);
    }
}
