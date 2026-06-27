package com.adi.jjit.Model;

import java.util.List;

public class ExtractedJobData {
    public String slug;
    public String workplaceType;
    public String experienceLevel;
    public List<EmploymentType> employmentTypes;
    public List<Skill> requiredSkills;


    public static class EmploymentType {
        public int from;
        public int to;
        public String currency;
        public String type;
    }

    public static class Skill {
        public String name;
    }
}
