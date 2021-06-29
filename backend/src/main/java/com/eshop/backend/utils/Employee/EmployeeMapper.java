package com.eshop.backend.DAO.DataAccess.Employee;

import com.eshop.backend.DAO.Models.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
       Employee employee = new Employee();

       employee.setId(resultSet.getString("id"));
       employee.setFirstName(resultSet.getString("firstName"));
       employee.setLastName(resultSet.getString("lastName"));
       employee.setEmail(resultSet.getString("email"));
       employee.setPhoneNumber(resultSet.getString("phoneNumber"));
       employee.setRole(resultSet.getString("role"));
       employee.setStatus(resultSet.getString("status"));

       return employee;
    }
}
