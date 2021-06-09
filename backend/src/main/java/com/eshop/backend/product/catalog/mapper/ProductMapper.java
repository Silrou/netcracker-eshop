package com.eshop.backend.product.catalog.mapper;

import com.eshop.backend.dao.Models.AuthorizedUser;
import com.eshop.backend.dao.Models.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    public static final String SELECT_SQL = "select p.id, p.productname, " +
            "p.productamount, p.productprice, p.productdiscount, p.productdate, " +
            "p.productpict, p.productdescription, p.productstatus, " +
            "p.genre, p.covertype, p.author, p.language, p.publisher from product p ";

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("productname");
        int amount = rs.getInt("productamount");
        double price = rs.getDouble("productprice");
        double discount = rs.getDouble("productdiscount");
        Date date = rs.getDate("productdate");
        String pict = rs.getString("productpict");
        String description = rs.getString("productdescription");
        String status = rs.getString("productstatus");
        int genre = rs.getInt("genre");
        int coverType = rs.getInt("covertype");
        int author = rs.getInt("author");
        int language = rs.getInt("language");
        int publisher = rs.getInt("publisher");
        return new Product(id, name, amount, price, discount, date, pict, description, status, genre, coverType, author, language, publisher);
    }
}
