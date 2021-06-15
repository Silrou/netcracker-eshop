package com.eshop.backend.product.catalog.service.Categories.Language;

import com.eshop.backend.dao.DataAccess.Categories.Language.LanguageDao;
import com.eshop.backend.dao.Models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService{

    LanguageDao languageDao;

    @Autowired
    public LanguageServiceImpl (LanguageDao languageDao) {this.languageDao = languageDao;}

    @Override
    public void create(Language model) {

    }

    @Override
    public Language getById(Long id) {
        return languageDao.getById(id);
    }

    @Override
    public List<Language> getAll() {
        return languageDao.getAll();
    }

    @Override
    public void update(Language model) {

    }

    @Override
    public void delete(Long id) {

    }
}
