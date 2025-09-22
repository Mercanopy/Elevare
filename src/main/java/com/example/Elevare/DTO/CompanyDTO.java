package com.example.Elevare.DTO;
public class CompanyDTO {
    private String name;
    private String address;
    private String phone;
    private String googleMapLink;
    private Long userId;

    public CompanyDTO() {}

    public CompanyDTO(String name, String address, String phone, String googleMapLink, Long userId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.googleMapLink = googleMapLink;
        this.userId = userId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGoogleMapLink() { return googleMapLink; }
    public void setGoogleMapLink(String googleMapLink) { this.googleMapLink = googleMapLink; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
