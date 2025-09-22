package com.example.Elevare.DTO;
import java.util.List;

public class StudentVerificationDTO {
    private String firstName;
    private String lastName;
    private String universityName;
    private List<InternshipInfoDTO> internships;
    private String major;       // new
    private String resumeLink;  // new


    public StudentVerificationDTO() {}

    public StudentVerificationDTO(String firstName, String lastName, String universityName, List<InternshipInfoDTO> internships, String major, String resumeLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.universityName = universityName;
        this.internships = internships;
        this.major = major;
        this.resumeLink = resumeLink;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUniversityName() { return universityName; }
    public void setUniversityName(String universityName) { this.universityName = universityName; }

    public List<InternshipInfoDTO> getInternships() { return internships; }
    public void setInternships(List<InternshipInfoDTO> internships) { this.internships = internships; }
    public String major() { return major; }
    public void major(String major) { this.major = major; }
    public String resumeLink() { return resumeLink; }
    public void resumeLink(String resumeLink) { this.resumeLink = resumeLink; }

}

