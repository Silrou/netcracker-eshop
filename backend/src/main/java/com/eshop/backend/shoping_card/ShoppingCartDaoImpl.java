package com.eshop.backend.shoping_card;

import com.eshop.backend.product.dao.ProductMapper;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShoppingCartDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ProductModel model) {

    }

    @Override
    public ProductModel getById(Long id) {
        return null;
    }

    @Override
    public List<ProductModel> getAll() {
        return null;
    }

    @Override
    public void update(ProductModel model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ProductModel> getProductsByIds(List<Long> ids) {
        try {
            String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

            String sql = String.format("select id, productamount from product" +
                    " where id in (%s)", inSql);

            RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                    .id(rs.getLong("id"))
                    .productAmount(rs.getInt("productamount"))
                    .build();
            List<ProductModel> a = jdbcTemplate.query(sql, rowMapper, ids.toArray());
            return jdbcTemplate.query(sql, rowMapper, ids.toArray());
        } catch (Exception e) {
            String str = e.toString();
        }
        return null;
    }

    @Override
    public int getProductsAmountById(Long id) {
        try {
            String sql = "select productamount from product where id = ?";
            Integer amount = jdbcTemplate.queryForObject(sql, Integer.class, id);
            return amount == null ? 0 : amount;
        } catch (Exception e) {
            String str = e.toString();
        }
        return 0;
    }
}
