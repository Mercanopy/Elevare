package com.example.Elevare.Controller;

import com.example.Elevare.DTO.InternshipDTO;
import com.example.Elevare.Entity.Internship;
import com.example.Elevare.Service.InternshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping("/create")
    public Internship createInternship(@RequestBody InternshipDTO internshipDTO) {
        try {
            return internshipService.createInternship(internshipDTO);
        } catch (Exception e) {
            System.out.println("Error in InternshipController createInternship: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Internship updateInternship(@PathVariable Long id, @RequestBody InternshipDTO internshipDTO) {
        try {
            return internshipService.updateInternship(id, internshipDTO);
        } catch (Exception e) {
            System.out.println("Error in InternshipController updateInternship: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Internship getInternship(@PathVariable Long id) {
        try {
            return internshipService.getInternship(id);
        } catch (Exception e) {
            System.out.println("Error in InternshipController getInternship: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/all")
    public List<Internship> getAllInternships() {
        try {
            return internshipService.getAllInternships();
        } catch (Exception e) {
            System.out.println("Error in InternshipController getAllInternships: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteInternship(@PathVariable Long id) {
        try {
            return internshipService.deleteInternship(id);
        } catch (Exception e) {
            System.out.println("Error in InternshipController deleteInternship: " + e.getMessage());
            return false;
        }
    }
}
