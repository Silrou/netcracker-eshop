package com.eshop.backend.DAO.DataAccess.Employee;

import com.eshop.backend.DAO.DataAccess.MainDao;
import com.eshop.backend.DAO.Models.Employee;

public interface EmployeeDao extends MainDao<Employee> {
    Employee getByRole (String status);
    Employee getByStatus (String status);
    Employee getById (Long id);
}
