package com.eshop.backend.shoping_card;

import com.eshop.backend.auth.utils.Role;
import com.eshop.backend.product.dao.ProductMapper;
import com.eshop.backend.product.dao.models.ProductModel;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

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
        return Collections.emptyList();
    }

    @Override
    public void update(ProductModel model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteOrderCartById(Long id) {
        String sql = "DELETE FROM ordercart WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void deleteProductFromOrderCart(Long productId, Long orderCartId) {
        String sql = "delete from orderproduct where productid = ? and ordercardid = ?";
        try {
            jdbcTemplate.update(sql, productId, orderCartId);
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void addProductToCart(ProductModel productModel, Long orderCaryId) {
        try {
            String sql = "insert into orderproduct (productid, ordercardid, incardproductamount, incardproductprice)\n" +
                    "values (?,?,?,?)";
            jdbcTemplate.update(sql, productModel.getId(), orderCaryId, productModel.getProductAmount(),
                    productModel.getProductPrice());
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void updateStatusById(Long id, String status) {
        try {
            String sql = "update ordercart set orderstatus = ?\n" +
                    " WHERE id = ?";
            jdbcTemplate.update(sql, status, id);
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public List<ProductModel> getProductsByIds(List<Long> ids) {
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

        String sql = String.format("select id, productamount from product" +
                " where id in (%s)", inSql);

        RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                .id(rs.getLong("id"))
                .productAmount(rs.getInt("productamount"))
                .build();
        return jdbcTemplate.query(sql, rowMapper, ids.toArray());

    }

    @Override
    public List<ProductModel> getProductsByUserIdAndStatus(Long id) {
        String sql = "select productid, incardproductamount from orderproduct as op\n" +
                "inner join ordercart as oc on oc.id = op.ordercardid\n" +
                "where oc.userid = ? and orderstatus in ('RESERVED', 'UNRESERVED')";

        RowMapper<ProductModel> rowMapper = (rs, rowNum) -> ProductModel.builder()
                .id(rs.getLong("productid"))
                .productAmount(rs.getInt("incardproductamount"))
                .build();

        return jdbcTemplate.query(sql, rowMapper, id);

    }

    @Override
    public int getProductsAmountById(Long id) {
        String sql = "SELECT productamount FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

    @Override
    public void createOrderCart(OrderCartModel orderCartModel) {
        try {
            String SQL = "insert into ordercart (userid, courierid," +
                    "packagedescription, orderstatus, totalprice, username, deliverytime," +
                    "fulladdress, dontdisturb)\n" +
                    "values (?,?,?,?,?,?,?,?,?)";

            jdbcTemplate.update(SQL, orderCartModel.getUserId(), orderCartModel.getCourierId(),
                    orderCartModel.getPackageDescription(), orderCartModel.getOrderStatus(),
                    orderCartModel.getTotalPrice(), orderCartModel.getUserName(),
                    orderCartModel.getDeliveryTime(), orderCartModel.getFullAddress(),
                    orderCartModel.isDontDisturb());
        } catch (Exception e) {
            String str = e.toString();
        }


    }

    @Override
    public void createOrderProduct(OrderProductModel orderProductModel) {
        String SQL = "insert into orderproduct (productid, ordercardid," +
                "incardproductamount, incardproductprice)\n " +
                "values (?,?,?,?)";

        jdbcTemplate.update(SQL, orderProductModel.getProductId(), orderProductModel.getOrderCardId(),
                orderProductModel.getInCardProductAmount(), orderProductModel.getInCardProductPrice());


    }

    @Override
    public OrderCartModel getLastOrderCartByUserId(Long id) {
        String sql = "SELECT id, userid, courierid, packagedescription, " +
                "orderstatus, totalprice, username, deliverytime, fulladdress, " +
                "dontdisturb FROM ordercart where userid = ? order by id desc limit 1";

        RowMapper<OrderCartModel> rowMapper = (rs, rowNum) -> new OrderCartModel(
                rs.getLong("id"),
                rs.getLong("userid"),
                rs.getLong("courierid"),
                rs.getString("packagedescription"),
                rs.getString("orderstatus"),
                rs.getInt("totalprice"),
                rs.getString("username"),
                rs.getDate("deliverytime"),
                rs.getString("fulladdress"),
                rs.getBoolean("dontdisturb"));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);

    }

    @Override
    public void updateOrderCartTotalPrice(Long id, Integer totalPrice) {
        String SQL = "update ordercart set totalprice = ? where id = ?";
        jdbcTemplate.update(SQL, totalPrice, id);
    }

    @Override
    public Integer getAmountById(Long id, Long orderCardId) {
        try {
            String sql = "SELECT incardproductamount FROM orderproduct where productid = ?" +
                    " AND ordercardid = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, id, orderCardId);
        } catch (Exception e) {
            return 0;
        }

    }
}
