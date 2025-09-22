package com.example.Elevare.Repository;
import com.example.Elevare.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByUser_Username(String username);
}

