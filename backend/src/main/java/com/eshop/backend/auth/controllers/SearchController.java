package com.eshop.backend.auth.controllers;

import com.eshop.backend.DAO.DataAccess.Employee.EmployeeService;
import com.eshop.backend.DAO.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class SearchController {
    private final EmployeeService employeeService;
    @Autowired
    public SearchController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployees(){
//        return employeeService.getEmployees();
        return ("account/search");
    }

    @PostMapping("/user/new")
    public String create(@ModelAttribute("employee") Employee employee,
                         BindingResult bindingResult) {
        employeeService.createEmployee(employee);
        return "New employee is successfully added";
    }

}
