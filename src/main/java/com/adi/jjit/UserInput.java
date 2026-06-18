package com.adi.jjit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    public static FilterData userInput(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your core language ( C, C++ , Java etc ): ");
        String mainSkill = sc.nextLine().trim();

        int experience;
        while (true) {
            System.out.print("Enter your years of experience (e.g., 0, 2, 5): ");
            String expInput = sc.nextLine().trim();
            try {
                experience = Integer.parseInt(expInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a clean numeric digit.");
            }
        }

        List<String> supportingSkill = new ArrayList<>();
        while (true) {
            System.out.print("Add tech (or type 'DONE' to stop, e.g., Spring Boot, Docker): ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("DONE")) {
                break;
            }
            if (!input.isEmpty()) {
                String[] techs = input.split("\\s*,\\s*");
                for (String tech : techs) {
                    if (!tech.isEmpty()) {
                        supportingSkill.add(tech.toLowerCase());
                    }
                }
            }
        }

        List<String> skillToExclude = new ArrayList<>();
        while (true) {
            System.out.print("Add tech that you dont wanna include (or type 'DONE' to stop, e.g., PHP, Angular): ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("DONE")) {
                break;
            }
            if (!input.isEmpty()) {
                String[] techs = input.split("\\s*,\\s*");
                for (String tech : techs) {
                    if (!tech.isEmpty()) {
                        skillToExclude.add(tech.toLowerCase());
                    }
                }
            }
        }

        int minSalary;
        while (true) {
            System.out.print("Enter your minimum expected salary (e.g., 5000): ");
            String salaryInput = sc.nextLine().trim();
            try {
                minSalary = Integer.parseInt(salaryInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a clean numeric digit for salary.");
            }
        }

        System.out.print("Enter contract type (e.g., Full-time, Part-time, Contract): ");
        String contractType = sc.nextLine().trim();

        System.out.print("Enter working mode (e.g., Remote, Hybrid, On-site): ");
        String workingMode = sc.nextLine().trim();

        return new FilterData(mainSkill, experience, supportingSkill, skillToExclude, minSalary, contractType, workingMode);
    }
}
