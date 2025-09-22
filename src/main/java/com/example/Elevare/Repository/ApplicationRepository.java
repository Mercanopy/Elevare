package com.example.Elevare.Repository;
import com.example.Elevare.Entity.Application;
import com.example.Elevare.Entity.Student;
import com.example.Elevare.Entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudent(Student student);
    List<Application> findByInternship(Internship internship);
    Optional<Application> findByStudentAndInternship(Student student, Internship internship);
}
