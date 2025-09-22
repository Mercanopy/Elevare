package com.example.Elevare.DTO;
public class UniversityDTO {
    private String name;
    private String location;
    private String contactEmail;
    private String phone;
    private Long userId;

    public UniversityDTO() {}

    public UniversityDTO(String name, String location, String contactEmail, String phone, Long userId) {
        this.name = name;
        this.location = location;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.userId = userId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
