package com.example.Elevare.Repository;
import com.example.Elevare.Entity.Internship;
import com.example.Elevare.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findByCompany(Company company);
}
