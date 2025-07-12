package com.mrteesoft.cvbuilderai.models;

public class CVDocument {
    private int id;
    private String userId;
    private int templateId;
    private String cvTitle;
    private String createdAt;
    private String updatedAt;

    public CVDocument() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getTemplateId() { return templateId; }
    public void setTemplateId(int templateId) { this.templateId = templateId; }

    public String getCvTitle() { return cvTitle; }
    public void setCvTitle(String cvTitle) { this.cvTitle = cvTitle; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}