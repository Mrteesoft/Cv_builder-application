package com.mrteesoft.cvbuilderai.models;

public class CVTemplate {
    private int id;
    private String templateName;
    private String templateType;
    private String previewImagePath;
    private String layoutConfig;
    private boolean isPremium;
    private String createdAt;

    public CVTemplate() {}

    public CVTemplate(String templateName, String templateType) {
        this.templateName = templateName;
        this.templateType = templateType;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getTemplateType() { return templateType; }
    public void setTemplateType(String templateType) { this.templateType = templateType; }

    public String getPreviewImagePath() { return previewImagePath; }
    public void setPreviewImagePath(String previewImagePath) { this.previewImagePath = previewImagePath; }

    public String getLayoutConfig() { return layoutConfig; }
    public void setLayoutConfig(String layoutConfig) { this.layoutConfig = layoutConfig; }

    public boolean isPremium() { return isPremium; }
    public void setPremium(boolean premium) { isPremium = premium; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}