package com.example.Elevare.Controller;

import com.example.Elevare.DTO.CompanyDTO;
import com.example.Elevare.Entity.Company;
import com.example.Elevare.Service.CompanyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public Company createCompany(@RequestBody CompanyDTO companyDTO) {
        try {
            return companyService.createCompany(companyDTO);
        } catch (Exception e) {
            System.out.println("Error in CompanyController createCompany: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        try {
            return companyService.updateCompany(id, companyDTO);
        } catch (Exception e) {
            System.out.println("Error in CompanyController updateCompany: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Company getCompany(@PathVariable Long id) {
        try {
            return companyService.getCompany(id);
        } catch (Exception e) {
            System.out.println("Error in CompanyController getCompany: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCompany(@PathVariable Long id) {
        try {
            return companyService.deleteCompany(id);
        } catch (Exception e) {
            System.out.println("Error in CompanyController deleteCompany: " + e.getMessage());
            return false;
        }
    }
}
