package com.example.Elevare.Repository;
import com.example.Elevare.Entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {
    Optional<University> findByUser_Username(String username);
}

