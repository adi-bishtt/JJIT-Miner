package com.adi.jjit.Model;

import java.util.ArrayList;
import java.util.List;

public class FilterData {
    private final String mainSkill;
    private final int experience;
    private final List<String> supportingSkill;
    private final List<String> skillToExclude;
    private final int minSalary;
    private final String contractType;
    private final String workingMode;

    public FilterData(String mainSkill, int experience, List<String> supportingSkill, List<String> skillToExclude, int minSalary,String contractType, String workingMode) {
        if (mainSkill != null) {
            this.mainSkill = mainSkill.trim().toLowerCase();
        } else {
            this.mainSkill = "";
        }

        this.experience = experience;

        if (supportingSkill != null) {
            this.supportingSkill = supportingSkill;
        } else {
            this.supportingSkill = new ArrayList<>();
        }

        if (skillToExclude != null) {
            this.skillToExclude = skillToExclude;
        } else {
            this.skillToExclude = new ArrayList<>();
        }

        this.minSalary = minSalary;

        if (contractType != null) {
            this.contractType = contractType.trim().toLowerCase();
        } else {
            this.contractType = "";
        }

        if (workingMode != null) {
            this.workingMode = workingMode.trim().toLowerCase();
        } else {
            this.workingMode = "";
        }
    }


    public String getMainSkill() {
        return mainSkill;
    }

    public int getExperience() {
        return experience;
    }

    public List<String> getSupportingSkill() {
        return supportingSkill;
    }

    public List<String> getSkillToExclude() {
        return skillToExclude;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public String getContractType() {
        return contractType;
    }

    public String getWorkingMode() {
        return workingMode;
    }

    @Override
    public String toString() {
        return "FilterData{" +
                "mainSkill='" + mainSkill + '\'' +
                ", experience=" + experience +
                ", supportingSkill=" + supportingSkill +
                ", skillToExclude=" + skillToExclude +
                ", minSalary=" + minSalary +
                ", contractType='" + contractType + '\'' +
                ", workingMode='" + workingMode + '\'' +
                '}';
    }
}
