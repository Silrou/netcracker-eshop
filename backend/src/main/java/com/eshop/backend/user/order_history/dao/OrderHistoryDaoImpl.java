package com.eshop.backend.user.order_history.dao;

import com.eshop.backend.order_card.dao.models.OrderCardModel;
import com.eshop.backend.product.dao.ProductMapper;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderHistoryDaoImpl implements OrderHistoryDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderHistoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(OrderCardModel model) {

    }

    @Override
    public OrderCardModel getById(Long id) {
        return null;
    }

    @Override
    public List<OrderCardModel> getAll() {

        return null;
    }

    @Override
    public void update(OrderCardModel model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<OrderCardModel> getAllByUserId(Long id) {
        try {
            String sql = "SELECT * FROM ordercart WHERE userid = ?";
            RowMapper<OrderCardModel> rowMapper = (rs, rowNum) -> OrderCardModel.builder()
                    .id(rs.getLong("id"))
                    .userId(rs.getLong("userid"))
                    .courierId(rs.getLong("courierid"))
                    .packageDescription(rs.getString("packagedescription"))
                    .orderStatus(rs.getString("orderstatus"))
                    .totalPrice(rs.getInt("totalprice"))
                    .userName(rs.getString("username"))
                    .deliveryTime(rs.getDate("deliverytime"))
                    .fullAddress(rs.getString("fulladdress"))
                    .dontDisturb(rs.getBoolean("dontdisturb"))
                    .build();
            return jdbcTemplate.query(sql, rowMapper, id);
        } catch (Exception e) {
            String str = e.toString();
        }
        return null;
    }
}
