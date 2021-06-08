package com.eshop.backend.DAO.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizedUserTableData {
    private String userName;
    private String userSurname;
    private String userRole;
    private String userStatus;
}
