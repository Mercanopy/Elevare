package com.example.Elevare.DTO;
import java.time.LocalDate;

public class ApplicationDTO {
    private Long studentId;
    private Long internshipId;
    private String status;
    private LocalDate appliedDate;

    public ApplicationDTO() {}

    public ApplicationDTO(Long studentId, Long internshipId, String status, LocalDate appliedDate) {
        this.studentId = studentId;
        this.internshipId = internshipId;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }
}
