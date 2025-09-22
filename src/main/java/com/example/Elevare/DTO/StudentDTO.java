package com.example.Elevare.DTO;

public class StudentDTO {

    private Long id;            // optional when returning
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String resumeLink;
    private Long userId;
    private Long universityId;

    public StudentDTO() {}

    public StudentDTO(Long id, String firstName, String lastName, String email,
                      String major, String resumeLink, Long userId, Long universityId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
        this.resumeLink = resumeLink;
        this.userId = userId;
        this.universityId = universityId;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getResumeLink() { return resumeLink; }
    public void setResumeLink(String resumeLink) { this.resumeLink = resumeLink; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getUniversityId() { return universityId; }
    public void setUniversityId(Long universityId) { this.universityId = universityId; }
}
