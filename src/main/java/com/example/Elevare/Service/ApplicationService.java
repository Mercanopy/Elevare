package com.example.Elevare.Service;

import com.example.Elevare.Entity.Application;
import com.example.Elevare.Entity.Student;
import com.example.Elevare.Entity.Internship;
import com.example.Elevare.Repository.ApplicationRepository;
import com.example.Elevare.Repository.StudentRepository;
import com.example.Elevare.Repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;

    public ApplicationService(ApplicationRepository applicationRepository, StudentRepository studentRepository, InternshipRepository internshipRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.internshipRepository = internshipRepository;
    }

    public Application applyForInternship(Long studentId, Long internshipId) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Internship internship = internshipRepository.findById(internshipId)
                    .orElseThrow(() -> new RuntimeException("Internship not found"));

            Application application = new Application();
            application.setStudent(student);
            application.setInternship(internship);

            return applicationRepository.save(application);
        } catch (Exception e) {
            System.out.println("Error creating application: " + e.getMessage());
            return null;
        }
    }

    public List<Application> getApplicationsByStudent(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            return student.getApplications();
        } catch (Exception e) {
            System.out.println("Error fetching applications: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteApplication(Long applicationId) {
        try {
            Application application = applicationRepository.findById(applicationId)
                    .orElseThrow(() -> new RuntimeException("Application not found"));
            applicationRepository.delete(application);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting application: " + e.getMessage());
            return false;
        }
    }
}

