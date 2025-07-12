package com.mrteesoft.cvbuilderai.models;

public class WorkExperience {
    private int id;
    private int cvId;
    private String companyName;
    private String jobTitle;
    private String startDate;
    private String endDate;
    private boolean isCurrent;
    private String location;
    private String description;
    private int displayOrder;

    public WorkExperience() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCvId() { return cvId; }
    public void setCvId(int cvId) { this.cvId = cvId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public boolean isCurrent() { return isCurrent; }
    public void setCurrent(boolean current) { isCurrent = current; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}