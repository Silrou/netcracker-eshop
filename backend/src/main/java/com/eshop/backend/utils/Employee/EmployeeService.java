package com.eshop.backend.utils.Employee;

import com.eshop.backend.DAO.DataAccess.Employee.EmployeeMapper;
import com.eshop.backend.DAO.Models.Employee;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.sql.*;
import java.util.List;
// import java.util.List;

@Service


public class EmployeeService {

    private JdbcTemplate jdbcTemplate;
    private ResultSet rs;


    @Autowired
    public EmployeeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<AuthorizedUserModel> getEmployees() {
        String sql = ("SELECT * FROM authorizeduser");
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }


    public Boolean createEmployee(AuthorizedUserModel authorizedUserModel) {
        String sql = ("INSERT INTO authorizeduser (id,username, username, userlogin, usernumber,userrole, userstatus) VALUES(?,?,?,?,?,?,?,?,?)");

        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, authorizedUserModel.getId());
                ps.setString(2, authorizedUserModel.getUserName());
                ps.setString(3, authorizedUserModel.getUserSurname());
                ps.setString(4, authorizedUserModel.getUserLogin());
                ps.setString(5, authorizedUserModel.getUserNumber());
                ps.setString(6, authorizedUserModel.getUserRole());
                authorizedUserModel.setUserStatus("Active");
                ps.setString(7, authorizedUserModel.getUserStatus());
                authorizedUserModel.setUserPassword("password");
                authorizedUserModel.setUserRegistrationDate(new java.sql.Date(new java.util.Date().getTime()));
                return ps.execute();


            }

        });
    }

//    public Boolean deleteEmployee(Long id) {
//        String sql = ("DELETE FROM employee WHERE id=?");
//        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
//            @Override
//            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//                ps.setLong(1, id);
//                return ps.execute();
//            }
//
//        });
//
//    }
//
//
//    public Boolean editEmployee(Employee employee) {
//        String sql = ("UPDATE employee SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, role = ?, status = ? WHERE id =?");
//
//        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
//            @Override
//            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//
//                ps.setString(1, employee.getFirstName());
//                ps.setString(2, employee.getLastName());
//                ps.setString(3, employee.getEmail());
//                ps.setString(4, employee.getPhoneNumber());
//                ps.setString(5, employee.getRole());
//                ps.setString(6, employee.getStatus());
//                ps.setString(7, employee.getId());
//                return ps.execute();
//            }
//
//        });
//
//    }


}

