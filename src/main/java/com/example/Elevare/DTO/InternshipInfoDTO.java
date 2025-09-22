package com.example.Elevare.DTO;
import java.time.LocalDate;

public class InternshipInfoDTO {
    private String title;
    private String description;
    private String field;
    private LocalDate startDate;
    private LocalDate endDate;
    private CompanyInfoDTO company;

    public InternshipInfoDTO() {}

    public InternshipInfoDTO(String title, String description, String field, LocalDate startDate, LocalDate endDate, CompanyInfoDTO company) {
        this.title = title;
        this.description = description;
        this.field = field;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public CompanyInfoDTO getCompany() { return company; }
    public void setCompany(CompanyInfoDTO company) { this.company = company; }
}

