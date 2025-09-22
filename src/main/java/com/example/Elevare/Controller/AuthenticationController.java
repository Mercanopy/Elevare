package com.example.Elevare.Controller;

import com.example.Elevare.Config.CustomUserDetailsService;
import com.example.Elevare.Config.JwtUtility;
import com.example.Elevare.DTO.CompanyRegisterRequest;
import com.example.Elevare.DTO.LoginRequest;
import com.example.Elevare.DTO.StudentRegisterRequest;
import com.example.Elevare.DTO.UserResponseDTO;
import com.example.Elevare.Entity.Company;
import com.example.Elevare.Entity.Role;
import com.example.Elevare.Entity.Student;
import com.example.Elevare.Entity.User;
import com.example.Elevare.Repository.CompanyRepository;
import com.example.Elevare.Repository.StudentRepository;
import com.example.Elevare.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtility jwtUtility;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            CustomUserDetailsService userDetailsService,
            JwtUtility jwtUtility,
            UserRepository userRepository,
            StudentRepository studentRepository,
            CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtility = jwtUtility;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // -----------------------
    // LOGIN -> returns JWT
    // -----------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            Map<String, String> err = new HashMap<>();
            err.put("error", "Invalid username or password");
            return ResponseEntity.status(401).body(err);
        }

        // load user details and create token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtility.generateToken(userDetails); // expects UserDetails; if your JwtUtil uses username, change accordingly

        Map<String, Object> resp = new HashMap<>();
        resp.put("token", token);

        // optional: include basic user info
        Optional<User> u = userRepository.findByUsername(request.getUsername());
        u.ifPresent(user -> {
            resp.put("username", user.getUsername());
            resp.put("role", user.getRole() != null ? user.getRole().name() : null);
            resp.put("userId", user.getId());
        });

        return ResponseEntity.ok(resp);
    }

    // -----------------------
    // REGISTER STUDENT
    // -----------------------
    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRegisterRequest req) {
        try {
            // validation: username/email unique
            if (userRepository.existsByUsername(req.getUsername())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
            }
            if (req.getEmail() != null && userRepository.existsByEmail(req.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
            }

            // create user
            User user = new User();
            user.setUsername(req.getUsername());
            user.setEmail(req.getEmail());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setRole(Role.STUDENT);
            user = userRepository.save(user);

            // create student profile
            Student student = new Student();
            student.setUser(user);
            student.setFirstName(req.getFirstName());
            student.setLastName(req.getLastName());
            student.setMajor(req.getMajor());
            student.setResumeLink(req.getResumeLink());
            // if your Student entity has university relationship, set it here (e.g., student.setUniversity(...))
            studentRepository.save(student);

            UserResponseDTO out = new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(out);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Registration failed: " + e.getMessage()));
        }
    }

    // -----------------------
    // REGISTER COMPANY
    // -----------------------
    @PostMapping("/register/company")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanyRegisterRequest req) {
        try {
            // validation
            if (userRepository.existsByUsername(req.getUsername())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
            }
            if (req.getEmail() != null && userRepository.existsByEmail(req.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
            }

            // create user
            User user = new User();
            user.setUsername(req.getUsername());
            user.setEmail(req.getEmail());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setRole(Role.COMPANY);
            user = userRepository.save(user);

            // create company profile
            Company company = new Company();
            company.setUser(user);
            company.setName(req.getName());
            company.setAddress(req.getAddress());
            company.setPhone(req.getPhone());
            company.setGoogleMapLink(req.getGoogleMapLink());
            companyRepository.save(company);

            UserResponseDTO out = new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(out);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Registration failed: " + e.getMessage()));
        }
    }
}
