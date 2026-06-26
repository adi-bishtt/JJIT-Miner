package com.adi.jjit.Engine;

import com.adi.jjit.Model.ExtractedJobData;
import com.adi.jjit.Model.FilterData;

import java.util.ArrayList;
import java.util.List;

public class JobMatcher {
    public static boolean isMatch(ExtractedJobData job, FilterData filter) {
        if (job == null || filter == null) return false;

        if (job.requiredSkills != null) {
            for (ExtractedJobData.Skill skill : job.requiredSkills) {
                if (filter.getSkillToExclude().contains(skill.name.toLowerCase())) {
                    return false;
                }
            }
        }

        if (job.experienceLevel != null) {
            String level = job.experienceLevel.toLowerCase().trim();
            int maxAllowedExp = filter.getExperience() + 2;

            if (level.equals("senior") && maxAllowedExp < 5) {
                return false;
            }
            if (level.equals("mid") && maxAllowedExp < 3) {
                return false;
            }
        }

        if (!filter.getWorkingMode().isEmpty() && job.workplaceType != null) {
            if (!job.workplaceType.toLowerCase().contains(filter.getWorkingMode())) {
                return false;
            }
        }

        if (filter.getMinSalary() > 0 && job.employmentTypes != null && !job.employmentTypes.isEmpty()) {
            boolean salaryOk = false;
            for (ExtractedJobData.EmploymentType emp : job.employmentTypes) {
                if (emp.currency != null && emp.currency.equalsIgnoreCase("PLN")) {
                    if (emp.to >= filter.getMinSalary() || emp.from >= filter.getMinSalary()) {
                        salaryOk = true;
                        break;
                    }
                }
            }
            if (!salaryOk) return false;
        }

        List<String> userDesiredSkills = new ArrayList<>();
        if (!filter.getMainSkill().isEmpty()) {
            userDesiredSkills.add(filter.getMainSkill().toLowerCase());
        }
        if (filter.getSupportingSkill() != null) {
            for (String supSkill : filter.getSupportingSkill()) {
                if (!supSkill.isEmpty()) {
                    userDesiredSkills.add(supSkill.toLowerCase());
                }
            }
        }

        if (!userDesiredSkills.isEmpty()) {
            int matchCount = 0;
            if (job.requiredSkills != null) {
                for (ExtractedJobData.Skill jobSkill : job.requiredSkills) {
                    String jobSkillName = jobSkill.name.toLowerCase();
                    for (String userSkill : userDesiredSkills) {
                        if (jobSkillName.contains(userSkill) || userSkill.contains(jobSkillName)) {
                            matchCount++;
                            break;
                        }
                    }
                }
            }

            double matchPercentage = ((double) matchCount / userDesiredSkills.size()) * 100;
            if (matchPercentage < 50.0) {
                return false;
            }
        }

        return true;
    }
}