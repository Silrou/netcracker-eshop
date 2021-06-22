package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(ProductModel productModel) {
        String SQL = "insert into product (productname, productamount,\n" +
                "                     productprice, productdiscount,\n" +
                "                     productdate, productpict, productdescription,\n" +
                "                     productstatus, genre, " +
                "                     covertype, author," +
                "                     language, publisher)\n" +
                "                     values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                productModel.getProductPrice(), productModel.getProductDiscount(),
                new Date(System.currentTimeMillis()), productModel.getProductPict(), productModel.getProductDescription(),
                productModel.getProductStatus(), productModel.getGenre(),
                productModel.getCoverType(), productModel.getAuthor(),
                productModel.getLanguage(), productModel.getPublisher());

    }

    @Override
    public ProductModel getById(Long id) {
        String sql = ProductMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new ProductMapper(), new Object[]{Long.valueOf(id)});
//        return template.queryForObject(sql, (rs, rowNum) -> {
//            Long id1 = rs.getLong("id");
//            String name = rs.getString("productname");
//            int amount = rs.getInt("productamount");
//            double price = rs.getDouble("productprice");
//            double discount = rs.getDouble("productdiscount");
//            Date date = rs.getDate("productdate");
//            String pict = rs.getString("productpict");
//            String description = rs.getString("productdescription");
//            String status = rs.getString("productstatus");
//            Long genre = rs.getLong("genre");
//            Long coverType = rs.getLong("covertype");
//            Long author = rs.getLong("author");
//            Long language = rs.getLong("language");
//            Long publisher = rs.getLong("publisher");
//            return new ProductModel(id1, name, amount, price, discount, date, pict, description, status, genre, coverType, author, language, publisher);
//        },new Object[]{id});
    }

    @Override
    public List<ProductModel> getByName(String name) {
        String sql = ProductMapper.SELECT_SQL +
                " WHERE p.productname ILIKE '%" + name + "%'";
        return template.query(sql, new ProductMapper());
    }

    @Override
    public Long getCount() {
        String sql = "SELECT COUNT(*) FROM product";
        return template.queryForObject(sql, Long.class);
    }

    @Override
    public List<ProductModel> getAll() {
        return null;
    }

    @Override
    public List<ProductModel> getProductPage(int page, int size) {
        String sql = ProductMapper.SELECT_SQL +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }

    @Override
    public void update(ProductModel productModel) {
        String SQL = "update product set productname = ?,\n" +
                "                   productamount= ?,\n" +
                "                   productprice = ?,\n" +
                "                   productdiscount = ?,\n" +
                "                   productdate = ?,\n" +
                "                   productpict = ?,\n" +
                "                   productdescription = ?,\n" +
                "                   productstatus = ?,\n" +
                "                   genre = ?,\n" +
                "                   covertype = ?,\n" +
                "                   author = ?,\n" +
                "                   language = ?,\n" +
                "                   publisher = ? \n" +
                "                   where id = ?";
        try {
            template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                    productModel.getProductPrice(), productModel.getProductDiscount(),
                    productModel.getProductDate(), productModel.getProductPict(),
                    productModel.getProductDescription(), productModel.getProductStatus(),
                    productModel.getGenre(), productModel.getCoverType(),
                    productModel.getAuthor(), productModel.getLanguage(),
                    productModel.getPublisher(), productModel.getId());
        } catch (Exception e) {
            String str = e.toString();
        }
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
    public List<ProductModel> getAllOrderByWithFilters(int page, int size, String orderBy, List<String> filter) {
        String inSql = String.join(",", Collections.nCopies(filter.size(), "?"));
        String sql = String.format(ProductMapper.SELECT_SQL +
                " left join productcategory on p.productcategory = productcategory.id" +
                " where productcategory.productcategoryname in (%s) order by p." + orderBy +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY", inSql);
        return template.query(sql, new ProductMapper(), filter.toArray());
    }

    @Override
    public List<ProductModel> getAllOrderBy(int page, int size, String orderBy) {
        String sql = ProductMapper.SELECT_SQL + " order by p." + orderBy +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }


}
