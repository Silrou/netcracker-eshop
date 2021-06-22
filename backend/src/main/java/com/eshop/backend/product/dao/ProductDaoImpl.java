package com.eshop.backend.product.dao;

import com.eshop.backend.product.dao.models.FilterModel;
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
                "                     productdate, productdescription,\n" +
                "                     productstatus, genre, " +
                "                     covertype, author," +
                "                     language, publisher)\n" +
                "                     values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            template.update(SQL, productModel.getProductName(), productModel.getProductAmount(),
                    productModel.getProductPrice(), productModel.getProductDiscount(),
                    productModel.getProductDate(), productModel.getProductDescription(),
                    productModel.getProductStatus(), productModel.getGenre(),
                    productModel.getCoverType(), productModel.getAuthor(),
                    productModel.getLanguage(), productModel.getPublisher());
        } catch (Exception e) {
            String str = e.toString();
        }
    }

    @Override
    public ProductModel getById(Long id) {
        String sql = ProductMapper.SELECT_SQL + "where id = ?";
        return template.queryForObject(sql, new ProductMapper(), new Object[]{Long.valueOf(id)});
    }

    @Override
    public List<ProductModel> getByName(String name) {
        String sql = ProductMapper.SELECT_SQL +
                " WHERE p.productname ILIKE '%" + name + "%'";
        return template.query(sql, new ProductMapper());
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

    @Override
    public List<ProductModel> getFiltered(int page, int size, FilterModel filterModel) {
        String sql = ProductMapper.SELECT_SQL +
                  filterSqlBuilder(filterModel) + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return template.query(sql, new ProductMapper());
    }

    private String filterSqlBuilder(FilterModel filterModel) {
        if ((filterModel.getAuthor().length == 0)&&
                (filterModel.getGenre().length == 0)&&
                (filterModel.getLanguage().length == 0)&&
                (filterModel.getPublisher().length == 0)&&
                (filterModel.getCoverType().length == 0)){
            return "";
        }
        StringBuilder filters = new StringBuilder();
        filters.append(" WHERE ");
        filterInBuilder(filters, filterModel.getAuthor(), " p.author ");
        filterInBuilder(filters, filterModel.getCoverType(), " p.covertype ");
        filterInBuilder(filters, filterModel.getGenre(), " p.genre ");
        filterInBuilder(filters, filterModel.getLanguage(), " p.language ");
        filterInBuilder(filters, filterModel.getPublisher(), " p.publisher ");
        return filters.substring(0, filters.length() - 4);
    }

    private void filterInBuilder(StringBuilder stringBuilder, Long[] foreignKeys, String fkName) {
        if (foreignKeys.length != 0) {
            stringBuilder.append(fkName);
            stringBuilder.append(" IN (");
            for (int i = 0; i < foreignKeys.length; i++) {
                stringBuilder.append(foreignKeys[i]);
                if (foreignKeys.length - i > 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(")" + " AND ");
        }
    }
}
