package com.example.Elevare.Controller;

import com.example.Elevare.DTO.StudentVerificationDTO;
import com.example.Elevare.Service.UniversityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/verify-student/{username}")
    public StudentVerificationDTO verifyStudent(@PathVariable String username) {
        try {
            StudentVerificationDTO dto = universityService.verifyStudentInternship(username);
            if (dto == null) {
                System.out.println("No student found for username: " + username);
            }
            return dto;
        } catch (Exception e) {
            System.out.println("Error in UniversityController: " + e.getMessage());
            return null;
        }
    }
}
