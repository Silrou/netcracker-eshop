package com.eshop.backend.product.catalog.service.Categories.Genre;

import com.eshop.backend.dao.DataAccess.Categories.Genre.GenreDao;
import com.eshop.backend.dao.Models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImp implements GenreService{

    GenreDao genreDao;

    @Autowired
    public GenreServiceImp (GenreDao genreDao) {this.genreDao = genreDao;}

    @Override
    public void create(Genre model) {

    }

    @Override
    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void update(Genre model) {

    }

    @Override
    public void delete(Long id) {

    }
}
