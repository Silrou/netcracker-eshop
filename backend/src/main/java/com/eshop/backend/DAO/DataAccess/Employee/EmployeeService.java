package com.eshop.backend.DAO.DataAccess.Employee;

import com.eshop.backend.DAO.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

@Service


public class EmployeeService {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getEmployees() {
        return "All employees are here.";
    }

    @Autowired
    JdbcTemplate template;

    public void createEmployee(Employee employee) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Employee VALUES(1,?,?,?,?,?,?)");

        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setString(3, employee.getEmail());
        ps.setString(4, employee.getPhoneNumber());
        ps.setString(5, employee.getRole());
        ps.setString(6, employee.getStatus());
        ps.executeUpdate();
    }
       catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}
