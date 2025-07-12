package com.mrteesoft.cvbuilderai.models;

public class PersonalInfo {
    private int id;
    private int cvId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String profileImagePath;
    private String professionalSummary;
    private String linkedinUrl;
    private String websiteUrl;

    public PersonalInfo() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCvId() { return cvId; }
    public void setCvId(int cvId) { this.cvId = cvId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getProfileImagePath() { return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }

    public String getProfessionalSummary() { return professionalSummary; }
    public void setProfessionalSummary(String professionalSummary) { this.professionalSummary = professionalSummary; }

    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }

    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
}