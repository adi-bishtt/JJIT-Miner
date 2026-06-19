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

        List<String> jobUrls = fetchJobUrls();
        for(int i = 0;i < jobUrls.toArray().length;i++){
            System.out.println("Job number " + i + " " + jobUrls.get(i));
        }
    }
}
