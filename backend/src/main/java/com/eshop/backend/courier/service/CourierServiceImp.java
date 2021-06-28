package com.eshop.backend.courier.service;

import com.eshop.backend.auth.dao.user.AuthorizedUserDao;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImp implements CourierService {
    AuthorizedUserDao authorizedUserDao;

    public CourierServiceImp(AuthorizedUserDao authorizedUserDao) {this.authorizedUserDao = authorizedUserDao;}

    @Override
    public List<AuthorizedUserModel> getById(long id) {return null;}

    @Override
    public List<AuthorizedUserModel> getMyTimeTable(long courierid) {
        String TimeTableSQL ="SELECT column_name as Dtime\n" +
                "FROM information_schema.columns\n" +
                "WHERE\n" +
                "table_name = 'couriercalendar'\n" +
                "and\n" +
                "column_name ILIKE 'f%';\n";
        String Couriercalendar ="SELECT *\n" +
                "FROM couriercalendar\n" +
                "WHERE courierid = ?;\n";
        return null;
    }


}
