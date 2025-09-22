package com.example.Elevare.Service;

import com.example.Elevare.DTO.InternshipDTO;
import com.example.Elevare.Entity.Internship;
import com.example.Elevare.Entity.Company;
import com.example.Elevare.Repository.InternshipRepository;
import com.example.Elevare.Repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;

    public InternshipService(InternshipRepository internshipRepository, CompanyRepository companyRepository) {
        this.internshipRepository = internshipRepository;
        this.companyRepository = companyRepository;
    }

    public Internship createInternship(InternshipDTO internshipDTO) {
        try {
            Company company = companyRepository.findById(internshipDTO.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));

            Internship internship = new Internship();
            internship.setTitle(internshipDTO.getTitle());
            internship.setDescription(internshipDTO.getDescription());
            internship.setField(internshipDTO.getField());
            internship.setStartDate(internshipDTO.getStartDate());
            internship.setEndDate(internshipDTO.getEndDate());
            internship.setCompany(company);

            return internshipRepository.save(internship);
        } catch (Exception e) {
            System.out.println("Error creating internship: " + e.getMessage());
            return null;
        }
    }

    public Internship updateInternship(Long internshipId, InternshipDTO internshipDTO) {
        try {
            Internship internship = internshipRepository.findById(internshipId)
                    .orElseThrow(() -> new RuntimeException("Internship not found"));

            internship.setTitle(internshipDTO.getTitle());
            internship.setDescription(internshipDTO.getDescription());
            internship.setField(internshipDTO.getField());
            internship.setStartDate(internshipDTO.getStartDate());
            internship.setEndDate(internshipDTO.getEndDate());

            return internshipRepository.save(internship);
        } catch (Exception e) {
            System.out.println("Error updating internship: " + e.getMessage());
            return null;
        }
    }

    public Internship getInternship(Long internshipId) {
        try {
            return internshipRepository.findById(internshipId)
                    .orElseThrow(() -> new RuntimeException("Internship not found"));
        } catch (Exception e) {
            System.out.println("Error fetching internship: " + e.getMessage());
            return null;
        }
    }

    public List<Internship> getAllInternships() {
        try {
            return internshipRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error fetching all internships: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteInternship(Long internshipId) {
        try {
            Internship internship = internshipRepository.findById(internshipId)
                    .orElseThrow(() -> new RuntimeException("Internship not found"));
            internshipRepository.delete(internship);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting internship: " + e.getMessage());
            return false;
        }
    }
}
