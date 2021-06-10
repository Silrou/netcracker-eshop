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
    @GetMapping("/admin/AllUsersMenu")
    public String getFilteredByStatusOn(Model model){
        model.addAttribute("getFilteredByStatusOn",authorizeduserService.getFilteredByStatusOn());
        return "getFilteredByStatusOn";
    }
    @GetMapping("/admin/AllUsersMenu")
    public String getFilteredByStatusOff(Model model){
        model.addAttribute("getFilteredByStatusOff",authorizeduserService.getFilteredByStatusOff());
        return "getFilteredByStatusOff";
    }
//    @GetMapping("/admin/AllUsersMenu")
//    public String getByName(Model model){
//        model.addAttribute("geByName",authorizeduserService.getByName());
//        return "geByName";
//    }
//    @GetMapping("/admin/AllUsersMenu")
//    public String getBySurname(Model model){
//        model.addAttribute("geBySurname",authorizeduserService.getBySurname());
//        return "geBySurname";
//    }
//    @GetMapping("/admin/AllUsersMenu")
//    public String getById(Model model){
//        model.addAttribute("getById",authorizeduserService.getById());
//        return "getById";
}
