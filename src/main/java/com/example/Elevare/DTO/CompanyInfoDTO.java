package com.example.Elevare.DTO;
public class CompanyInfoDTO {
    private String name;
    private String address;
    private String phone;
    private String googleMapLink;

    public CompanyInfoDTO() {}

    public CompanyInfoDTO(String name, String address, String phone, String googleMapLink) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.googleMapLink = googleMapLink;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGoogleMapLink() { return googleMapLink; }
    public void setGoogleMapLink(String googleMapLink) { this.googleMapLink = googleMapLink; }
}
