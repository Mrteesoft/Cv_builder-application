package com.mrteesoft.cvbuilderai.models;

public class Education {
    private int id;
    private int cvId;
    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private String startDate;
    private String endDate;
    private String gradeGpa;
    private String location;
    private String description;
    private int displayOrder;

    public Education() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCvId() { return cvId; }
    public void setCvId(int cvId) { this.cvId = cvId; }

    public String getInstitutionName() { return institutionName; }
    public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getFieldOfStudy() { return fieldOfStudy; }
    public void setFieldOfStudy(String fieldOfStudy) { this.fieldOfStudy = fieldOfStudy; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getGradeGpa() { return gradeGpa; }
    public void setGradeGpa(String gradeGpa) { this.gradeGpa = gradeGpa; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}