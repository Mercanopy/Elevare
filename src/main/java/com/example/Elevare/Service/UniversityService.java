package com.example.Elevare.Service;

import com.example.Elevare.DTO.StudentVerificationDTO;
import com.example.Elevare.DTO.InternshipInfoDTO;
import com.example.Elevare.DTO.CompanyInfoDTO;
import com.example.Elevare.Entity.Student;
import com.example.Elevare.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final StudentRepository studentRepository;

    public UniversityService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentVerificationDTO verifyStudentInternship(String username) {
        try {
            Student student = studentRepository.findByUser_Username(username)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            List<InternshipInfoDTO> internships = student.getApplications()
                    .stream()
                    .map(app -> {
                        try {
                            var internship = app.getInternship();
                            var company = internship.getCompany();

                            CompanyInfoDTO companyDTO = new CompanyInfoDTO(
                                    company.getName(),
                                    company.getAddress(),
                                    company.getPhone(),
                                    company.getGoogleMapLink()
                            );

                            return new InternshipInfoDTO(
                                    internship.getTitle(),
                                    internship.getDescription(),
                                    internship.getField(),
                                    internship.getStartDate(),
                                    internship.getEndDate(),
                                    companyDTO
                            );
                        } catch (Exception e) {
                            System.out.println("Error processing internship: " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(i -> i != null)
                    .collect(Collectors.toList());

            return new StudentVerificationDTO(
                    student.getFirstName(),
                    student.getLastName(),
                    student.getUniversity().getName(),
                    internships,
                    student.getMajor(),
                    student.getResumeLink()
            );
        } catch (Exception e) {
            System.out.println("Error verifying student: " + e.getMessage());
            return null;
        }
    }
}
