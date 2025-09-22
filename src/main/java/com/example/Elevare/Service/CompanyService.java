package com.example.Elevare.Service;

import com.example.Elevare.DTO.CompanyDTO;
import com.example.Elevare.Entity.Company;
import com.example.Elevare.Entity.User;
import com.example.Elevare.Repository.CompanyRepository;
import com.example.Elevare.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Company createCompany(CompanyDTO companyDTO) {
        try {
            User user = userRepository.findById(companyDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Company company = new Company();
            company.setName(companyDTO.getName());
            company.setAddress(companyDTO.getAddress());
            company.setPhone(companyDTO.getPhone());
            company.setGoogleMapLink(companyDTO.getGoogleMapLink());
            company.setUser(user);

            return companyRepository.save(company);
        } catch (Exception e) {
            System.out.println("Error creating company: " + e.getMessage());
            return null;
        }
    }

    public Company updateCompany(Long companyId, CompanyDTO companyDTO) {
        try {
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new RuntimeException("Company not found"));

            company.setName(companyDTO.getName());
            company.setAddress(companyDTO.getAddress());
            company.setPhone(companyDTO.getPhone());
            company.setGoogleMapLink(companyDTO.getGoogleMapLink());

            return companyRepository.save(company);
        } catch (Exception e) {
            System.out.println("Error updating company: " + e.getMessage());
            return null;
        }
    }

    public Company getCompany(Long companyId) {
        try {
            return companyRepository.findById(companyId)
                    .orElseThrow(() -> new RuntimeException("Company not found"));
        } catch (Exception e) {
            System.out.println("Error fetching company: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteCompany(Long companyId) {
        try {
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            companyRepository.delete(company);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting company: " + e.getMessage());
            return false;
        }
    }
}

