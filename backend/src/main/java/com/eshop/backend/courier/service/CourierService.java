package com.eshop.backend.courier.service;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import java.util.List;

public interface CourierService {
    public List<AuthorizedUserModel> getById(long id);
}
