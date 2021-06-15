package com.eshop.backend.dao.Controller;

import com.eshop.backend.dao.DataAccess.Services.AuthorizeduserService;
import com.eshop.backend.dao.Models.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuthorizeduserController {
    @Autowired
    AuthorizeduserService authorizeduserService;

//    @GetMapping("/admin/search")
//    public String getAllUsers(Model model){
//        model.addAttribute("getAllUsers",authorizeduserService.getAllUsers());
//        return "getAllUsers";
//    }
    @GetMapping("/admin/search")
    public ResponseEntity<List<AuthorizedUser>> getAllProduct() {
        List<AuthorizedUser> authorizedUser = authorizeduserService.getAllUsers();
        return new ResponseEntity<>(authorizedUser, HttpStatus.OK);
    }
    @GetMapping("/admin/AllUsersMenu3")
    public String getAllManager(Model model){
        model.addAttribute("getAllUsers",authorizeduserService.getAllManager());
        return "getAllManager";
    }
    @GetMapping("/admin/AllUsersMenu2")
    public String getAllCourier(Model model){
        model.addAttribute("getAllUsers",authorizeduserService.getAllCourier());
        return "getAllCourier";
    }
    @GetMapping("/admin/AllUsersMenu1")
    public String getFilteredByStatusOn(Model model){
        model.addAttribute("getFilteredByStatusOn",authorizeduserService.getFilteredByStatusOn());
        return "getFilteredByStatusOn";
    }
    @GetMapping("/admin/AllUsersMenu4")
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
