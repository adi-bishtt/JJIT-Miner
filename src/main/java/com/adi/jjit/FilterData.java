package com.adi.jjit;

import java.util.ArrayList;
import java.util.List;

public class FilterData {
    private final String mainSkill;
    private final int experience;
    private final List<String> supportingSkill;
    private final List<String> skillToExclude;
    private final int minSalary;
    private final String contractType;

    public FilterData(String mainSkill, int experience, List<String> supportingSkill, List<String> skillToExclude, int minSalary, String contractType) {
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

    @Override
    public String toString() {
        return "mainSkill='" + mainSkill + '\'' +
                ", experience=" + experience +
                ", supportingSkill=" + supportingSkill +
                ", skillToExclude=" + skillToExclude +
                ", minSalary=" + minSalary +
                ", contractType='" + contractType + '\'';
    }
}
