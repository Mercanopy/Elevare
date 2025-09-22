package com.example.Elevare.Controller;

import com.example.Elevare.DTO.StudentDTO;
import com.example.Elevare.Service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public StudentDTO createStudent(@RequestBody StudentDTO dto) {
        try {
            return studentService.createStudent(dto);
        } catch (Exception e) {
            System.out.println("Error in StudentController create: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        try {
            return studentService.updateStudent(id, dto);
        } catch (Exception e) {
            System.out.println("Error in StudentController update: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        try {
            return studentService.getStudentById(id);
        } catch (Exception e) {
            System.out.println("Error in StudentController getById: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/get-by-username/{username}")
    public StudentDTO getStudentByUsername(@PathVariable String username) {
        try {
            return studentService.getStudentByUsername(username);
        } catch (Exception e) {
            System.out.println("Error in StudentController getByUsername: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteStudent(@PathVariable Long id) {
        try {
            return studentService.deleteStudent(id);
        } catch (Exception e) {
            System.out.println("Error in StudentController delete: " + e.getMessage());
            return false;
        }
    }
}
