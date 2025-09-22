package com.example.Elevare.Controller;

import com.example.Elevare.DTO.UserResponseDTO;
import com.example.Elevare.Service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // List all users
    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        try {
            return adminService.getAllUsers();
        } catch (Exception e) {
            System.out.println("Error in AdminController getAllUsers: " + e.getMessage());
            return null;
        }
    }

    // Delete a user
    @DeleteMapping("/users/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        try {
            return adminService.deleteUser(id);
        } catch (Exception e) {
            System.out.println("Error in AdminController deleteUser: " + e.getMessage());
            return false;
        }
    }
}
