package com.example.Elevare.Service;

import com.example.Elevare.DTO.StudentDTO;
import com.example.Elevare.Entity.Student;
import com.example.Elevare.Entity.User;
import com.example.Elevare.Entity.University;
import com.example.Elevare.Repository.StudentRepository;
import com.example.Elevare.Repository.UserRepository;
import com.example.Elevare.Repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    public StudentService(StudentRepository studentRepository,
                          UserRepository userRepository,
                          UniversityRepository universityRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
    }

    public StudentDTO createStudent(StudentDTO dto) {
        try {
            Student student = new Student();
            student.setFirstName(dto.getFirstName());
            student.setLastName(dto.getLastName());
            student.setEmail(dto.getEmail());
            student.setMajor(dto.getMajor());
            student.setResumeLink(dto.getResumeLink());

            if (dto.getUserId() != null) {
                User user = userRepository.findById(dto.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                student.setUser(user);
            }

            if (dto.getUniversityId() != null) {
                University uni = universityRepository.findById(dto.getUniversityId())
                        .orElseThrow(() -> new RuntimeException("University not found"));
                student.setUniversity(uni);
            }

            Student saved = studentRepository.save(student);
            return toDto(saved);
        } catch (Exception e) {
            System.out.println("Error creating student: " + e.getMessage());
            return null;
        }
    }

    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            if (dto.getFirstName() != null) student.setFirstName(dto.getFirstName());
            if (dto.getLastName() != null) student.setLastName(dto.getLastName());
            if (dto.getEmail() != null) student.setEmail(dto.getEmail());
            if (dto.getMajor() != null) student.setMajor(dto.getMajor());
            if (dto.getResumeLink() != null) student.setResumeLink(dto.getResumeLink());

            if (dto.getUniversityId() != null) {
                University uni = universityRepository.findById(dto.getUniversityId())
                        .orElseThrow(() -> new RuntimeException("University not found"));
                student.setUniversity(uni);
            }

            Student saved = studentRepository.save(student);
            return toDto(saved);
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
            return null;
        }
    }

    public StudentDTO getStudentById(Long id) {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            return toDto(student);
        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
            return null;
        }
    }

    public StudentDTO getStudentByUsername(String username) {
        try {
            Optional<Student> opt = studentRepository.findByUser_Username(username);
            return opt.map(this::toDto).orElse(null);
        } catch (Exception e) {
            System.out.println("Error fetching student by username: " + e.getMessage());
            return null;
        }
    }

    public boolean deleteStudent(Long id) {
        try {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            studentRepository.delete(student);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    private StudentDTO toDto(Student s) {
        if (s == null) return null;
        Long userId = s.getUser() != null ? s.getUser().getId() : null;
        Long uniId = s.getUniversity() != null ? s.getUniversity().getId() : null;
        return new StudentDTO(
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getMajor(),
                s.getResumeLink(),
                userId,
                uniId
        );
    }
}
