package com.eshop.backend.admin;

import com.eshop.backend.admin.services.AdminService;
import com.eshop.backend.user.dao.models.AuthorizedUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

//    @GetMapping("/admin/search")
//    public String getAllUsers(Model model){
//        model.addAttribute("getAllUsers",authorizeduserService.getAllUsers());
//        return "getAllUsers";
//    }
    @GetMapping("/admin/search")
    public ResponseEntity<List<AuthorizedUserModel>> getAllProduct() {
        List<AuthorizedUserModel> authorizedUserModel = adminService.getAllUsers();
        return new ResponseEntity<>(authorizedUserModel, HttpStatus.OK);
    }
    @GetMapping("/admin/AllUsersMenu3")
    public String getAllManager(Model model){
        model.addAttribute("getAllUsers", adminService.getAllManager());
        return "getAllManager";
    }
    @GetMapping("/admin/AllUsersMenu2")
    public String getAllCourier(Model model){
        model.addAttribute("getAllUsers", adminService.getAllCourier());
        return "getAllCourier";
    }
    @GetMapping("/admin/AllUsersMenu1")
    public String getFilteredByStatusOn(Model model){
        model.addAttribute("getFilteredByStatusOn", adminService.getFilteredByStatusOn());
        return "getFilteredByStatusOn";
    }
    @GetMapping("/admin/AllUsersMenu4")
    public String getFilteredByStatusOff(Model model){
        model.addAttribute("getFilteredByStatusOff", adminService.getFilteredByStatusOff());
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
