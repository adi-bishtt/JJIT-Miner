package com.adi.jjit;

import java.util.List;

public class ExtractedJobData {
    public String slug;
    public String title;
    public String workplaceType;
    public String workingTime;
    public String experienceLevel;
    public Category category;
    public List<EmploymentType> employmentTypes;
    public List<Skill> requiredSkills;

    public static class Category {
        public String key;
    }

    public static class EmploymentType {
        public int from;
        public int to;
        public String currency;
        public String type;
    }

    public static class Skill {
        public String name;
        public int level;
    }
}
