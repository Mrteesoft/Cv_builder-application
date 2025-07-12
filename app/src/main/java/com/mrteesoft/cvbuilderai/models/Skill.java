package com.mrteesoft.cvbuilderai.models;

public class Skill {
    private int id;
    private int cvId;
    private String skillName;
    private String skillLevel;
    private String skillCategory;
    private int displayOrder;

    public Skill() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCvId() { return cvId; }
    public void setCvId(int cvId) { this.cvId = cvId; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getSkillLevel() { return skillLevel; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }

    public String getSkillCategory() { return skillCategory; }
    public void setSkillCategory(String skillCategory) { this.skillCategory = skillCategory; }

    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}