package com.adi.jjit;

import java.util.List;

import static com.adi.jjit.SiteDataFetcher.fetchJobUrls;
import static com.adi.jjit.UserInput.userInput;

public class Main {
    public static void main(String[] args) {
//        FilterData userInput = userInput();
//
//        System.out.println(userInput.getMainSkill());
//        System.out.println(userInput.getExperience());
//        System.out.println(userInput.getContractType());
//        System.out.println(userInput.getMinSalary());
//        System.out.println(userInput.getSupportingSkill());
//        System.out.println(userInput.getSkillToExclude());
//        System.out.println(userInput.getWorkingMode());
//
//        List<String> jobUrls = fetchJobUrls();
//        for(int i = 0;i < jobUrls.toArray().length;i++){
//            System.out.println("Job number " + i + " " + jobUrls.get(i));
//        }


        //https://justjoin.it/job-offer/cyclad-mendix-architect-senior-mendix-developer-katowice-java
        //https://justjoin.it/api/candidate-api/offers/cyclad-mendix-architect-senior-mendix-developer-katowice-java
        String testApiUrl = "https://justjoin.it/api/candidate-api/offers/cyclad-mendix-architect-senior-mendix-developer-katowice-java";
        ExtractedJobData job = JobDataExtractor.extractFields(testApiUrl);

        if (job == null) {
            System.out.println("Job processing failed.");
            return;
        }

        System.out.println("Slug: " + job.slug);
        System.out.println("Title: " + job.title);
        System.out.println("Workplace Type: " + job.workplaceType);
        System.out.println("Working Time: " + job.workingTime);
        System.out.println("Experience Level: " + job.experienceLevel);

        if (job.category != null) {
            System.out.println("Category Key: " + job.category.key);
        }

        if (job.requiredSkills != null) {
            for (ExtractedJobData.Skill skill : job.requiredSkills) {
                System.out.println(" - Skill: " + skill.name + " | Level: " + skill.level);
            }
        }

        if (job.employmentTypes != null) {
            for (ExtractedJobData.EmploymentType emp : job.employmentTypes) {
                System.out.println(" - Contract: " + emp.type + " | Min: " + emp.from + " | Max: " + emp.to + " | Currency: " + emp.currency);
            }
        }

    }
}
