package com.example.Elevare.Service;

import com.example.Elevare.DTO.UserResponseDTO;
import com.example.Elevare.Entity.User;
import com.example.Elevare.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<UserResponseDTO> getAllUsers() {
        try {
            return userRepository.findAll().stream()
                    .map(user -> new UserResponseDTO(
                            user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getRole().toString()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
            return null;
        }
    }

    // Delete a user
    public boolean deleteUser(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}
