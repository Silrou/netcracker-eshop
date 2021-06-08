package com.eshop.backend.DAO.Controller;

import com.eshop.backend.DAO.DataAccess.Services.AuthorizeduserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizeduserController {
    @Autowired
    AuthorizeduserService authorizeduserService;

    @GetMapping("/admin/AllUsersMenu")
    public String getAllUsers(Model model){
        model.addAttribute("getAllUsers",authorizeduserService.getAllUsers());
        return "getAllUsers";
    }
    @GetMapping("/admin/AllUsersMenu")
    public String getAllManager(Model model){
        model.addAttribute("getAllUsers",authorizeduserService.getAllManager());
        return "getAllManager";
    }
    @GetMapping("/admin/AllUsersMenu")
    public String getAllCourier(Model model){
        model.addAttribute("getAllUsers",authorizeduserService.getAllCourier());
        return "getAllCourier";
    }
}
