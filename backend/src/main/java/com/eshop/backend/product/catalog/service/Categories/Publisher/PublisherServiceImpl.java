package com.eshop.backend.product.catalog.service.Categories.Publisher;

import com.eshop.backend.dao.DataAccess.Categories.Publisher.PublisherDao;
import com.eshop.backend.dao.Models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService{

    PublisherDao publisherDao;

    @Autowired
    public PublisherServiceImpl (PublisherDao publisherDao) {this.publisherDao = publisherDao;}

    @Override
    public void create(Publisher model) {

    }

    @Override
    public Publisher getById(Long id) {
        return publisherDao.getById(id);
    }

    @Override
    public List<Publisher> getAll() {
        return publisherDao.getAll();
    }

    @Override
    public void update(Publisher model) {

    }

    @Override
    public void delete(Long id) {

    }
}
