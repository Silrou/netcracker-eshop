package com.eshop.backend.dao.DataAccess.product;

import com.eshop.backend.dao.models.Product;
import com.eshop.backend.product.catalog.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Product product) {
        String SQL = "insert into product (productname, productamount,\n" +
                "                     productprice, productdiscount,\n" +
                "                     productdate, productdescription,\n" +
                "                     productstatus, productcategory)\n" +
                "                     values (?,?,?,?,?,?,?,?)";
        try {
            template.update(SQL, product.getProductName(), product.getProductAmount(),
                    product.getProductPrice(), product.getProductDiscount(),
                    product.getProductDate(), product.getProductDescription(),
                    product.getProductStatus(), product.getProductCategory());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getProductPage(int page, int size) {
        try{
            String sql = ProductMapper.SELECT_SQL +
                " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatementCreator statementCreator = con -> {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, page);
                ps.setInt(2, size);
                return ps;
            };

        return template.query(statementCreator, new ProductMapper());
        } catch (Exception e) {
            String str = e.toString();
            return null;
        }

    }

    @Override
    public void update(Product product) {
//        String SQL = "update product set productname = ?,\n" +
//                "                   productamount= ?,\n" +
//                "                   productprice = ?,\n" +
//                "                   productdiscount = ?,\n" +
//                "                   productdate = ?,\n" +
//                "                   productdescription = ?,\n" +
//                "                   productstatus = ?,\n" +
//                "                   productcategory = ?\n" +
//                "                   where id = ?";
//        try {
//            template.update(SQL, product.getProductName(), product.getProductAmount(),
//                    product.getProductPrice(), product.getProductDiscount(),
//                    product.getProductDate(), product.getProductDescription(),
//                    product.getProductStatus(), product.getProductCategory(), product.getId());
//        } catch (Exception e) {
//            String str = e.toString();
//        }
    }

    @Override
    public void delete(Long id) {
//        try {
//            String SQL = "delete from product where id = ?";
//            template.update(SQL, id);
//        } catch (Exception e) {
//            String str = e.toString();
//        }
    }

    public void remove(Long id) {
//        String SQL = "update product set \"productStatus\" = false where id = ?";
//        template.update(SQL, id);
    }

    @Override
    public List<Product> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        return null;
    }

    @Override
    public List<Product> getAllOrderBy(int page, int size, String orderBy) {
        try {
            String sql = ProductMapper.SELECT_SQL + " order by ?" +
                    " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatementCreator statementCreator = con -> {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, orderBy);
                ps.setInt(2, page);
                ps.setInt(3, size);
                return ps;
            };
            return template.query(statementCreator, new ProductMapper());
        } catch (Exception e) {
            String str = e.toString();
            return null;
        }

    }

}
