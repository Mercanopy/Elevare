package com.example.Elevare.Controller;

import com.example.Elevare.Entity.Application;
import com.example.Elevare.Service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public Application applyForInternship(@RequestParam Long studentId, @RequestParam Long internshipId) {
        try {
            return applicationService.applyForInternship(studentId, internshipId);
        } catch (Exception e) {
            System.out.println("Error in ApplicationController applyForInternship: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getApplicationsByStudent(@PathVariable Long studentId) {
        try {
            return applicationService.getApplicationsByStudent(studentId);
        } catch (Exception e) {
            System.out.println("Error in ApplicationController getApplicationsByStudent: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteApplication(@PathVariable Long id) {
        try {
            return applicationService.deleteApplication(id);
        } catch (Exception e) {
            System.out.println("Error in ApplicationController deleteApplication: " + e.getMessage());
            return false;
        }
    }
}
