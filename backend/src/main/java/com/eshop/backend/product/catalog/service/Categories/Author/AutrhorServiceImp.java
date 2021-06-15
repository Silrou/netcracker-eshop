package com.eshop.backend.product.catalog.service.Categories.Author;


import com.eshop.backend.dao.DataAccess.Categories.Author.AuthorDao;
import com.eshop.backend.dao.Models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutrhorServiceImp implements AuthorService{

    AuthorDao authorDao;

    @Autowired
    public AutrhorServiceImp (AuthorDao authorDao) {this.authorDao = authorDao;}

    @Override
    public void create(Author model) {

    }

    @Override
    public Author getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void update(Author model) {

    }

    @Override
    public void delete(Long id) {

    }
}
